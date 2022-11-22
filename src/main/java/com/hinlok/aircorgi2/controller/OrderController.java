package com.hinlok.aircorgi2.controller;


import com.hinlok.aircorgi2.model.*;
import com.hinlok.aircorgi2.service.OrderService;
import com.hinlok.aircorgi2.service.TicketService;


import com.hinlok.aircorgi2.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;


    @RequestMapping("/flightDetail")
    public String flightDetail(@Param("flightOption") String flightOption, Model map, HttpServletRequest httpServletRequest) {

        String flightNum = flightOption.substring(0,6);
        String depDate = flightOption.substring(6);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        session.setAttribute("formerChoice",httpServletRequest.getRequestURI()+"?"+httpServletRequest.getQueryString());
        System.out.println(session.getAttribute("formerChoice").toString());

        List<MultiQuery> multiQueryResult = orderService.flightDetail(flightNum,depDate);

        map.addAttribute("multiQueryResult", multiQueryResult);

        String depAirportInfo = ticketService.queryDepartureAirport(orderService.selectDepByFlight(flightNum));
        String desAirportInfo = ticketService.queryDestinationAirport(orderService.selectDesByFlight(flightNum));


        map.addAttribute("departureAirport", depAirportInfo);
        map.addAttribute("destinationAirport", desAirportInfo);

        List<PassengerInfo> passengerInfoQuery = orderService.queryPassengerInfo(session.getAttribute("userId").toString());

        map.addAttribute("passenger", passengerInfoQuery);

        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "ordercheck";
        } else {
            return "ordercheck";
        }


    }



    @RequestMapping("/orderConfirm")
    public String orderConfirm(OrderInfo orderInfo,ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();


        /**
         * What's in the object orderInfo
         * flightNum,depDate,passenger1,passenger2,passenger3,seatType
         */

        /**
         *         Create orderId
         */
        Date currentTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = format.format(currentTime);

        Random r = new Random();

        String randomChars = "";
        for (int i = 0; i < 2; i++) {
            randomChars = randomChars + (char) (Math.random() * 26 + 'A');
        }

        String orderId =randomChars + dateString + r.nextInt(9) + r.nextInt(99);

        /**
         * Calculate bill
         */
        int passengerCount = 0;
        if (orderInfo.getPassenger3().length() != 0) {
            passengerCount++;
        }
        if (orderInfo.getPassenger2().length() != 0) {
            passengerCount++;
        }
        if (orderInfo.getPassenger1().length() != 0) {
            passengerCount++;
        }

        int priceAmount = orderService.calculateBill(orderInfo) * passengerCount;
        /**
         * Create order by orderId,currentTime,priceAmount,userId
         */
        String userId = session.getAttribute("userId").toString();
        int ticketAmount = passengerCount;

        int orderStat = 0;

        /*DateString for order*/
        SimpleDateFormat orderSimpleDateFormat = new SimpleDateFormat("y-M-d HH:mm:ss");
        String orderDateString = orderSimpleDateFormat.format(currentTime);


        orderService.createOrder(orderId,orderDateString,priceAmount,userId,ticketAmount,orderStat);

        orderService.appendOrder(orderInfo,orderId);

        /**
         * Query order information
         */

        List<OrderInfo> orderInfos = orderService.queryOrderInfo(orderId);
        map.addAttribute("orderInfos", orderInfos);


        List<MultiQuery> multiQueryResult = orderService.flightDetail(orderInfo.getFlightNum(),orderInfo.getDepDate());
        map.addAttribute("multiQueryResult", multiQueryResult);

        String depAirportInfo = ticketService.queryDepartureAirport(orderService.selectDepByFlight(orderInfo.getFlightNum()));
        String desAirportInfo = ticketService.queryDestinationAirport(orderService.selectDesByFlight(orderInfo.getFlightNum()));
        map.addAttribute("departureAirport", depAirportInfo);
        map.addAttribute("destinationAirport", desAirportInfo);

        /*Query user(contact) information*/
        map.addAttribute("userInfo", userService.findByUserId(session.getAttribute("userId").toString()));

        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "billpay";
        } else {
            return "billpay";
        }

    }


    /**
     * Create ticket by orderInfo after pay
     *
     * orderStat=0 NOT PAID
     * orderStat=1 Paid
     * orderStat=2 Completed
     */
    @RequestMapping("/orderPay")
    public String orderPay(@Param("orderId") String orderId,ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        int orderStat = 1;
        orderService.alterOrderStat(orderId, orderStat);

        List<OrderInfo> allOrderInfos = orderService.queryAllOrder(session.getAttribute("userId").toString());
        map.addAttribute("allOrderInfos", allOrderInfos);

        /*if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "orderlist";
        } else {
            return "orderlist";
        }*/
        return "redirect:/createTicket?orderId=" + orderId;


    }


    /**
     *Check order list by userId
     */
    @RequestMapping("/orderList")
    public String orderList(ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        List<OrderInfo> allOrderInfos = orderService.queryAllOrder(session.getAttribute("userId").toString());
        map.addAttribute("allOrderInfos", allOrderInfos);

        if (allOrderInfos.isEmpty()) {
            map.addAttribute("orderNotFound", "0");
        }

        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "orderlist";
        } else {
            return "redirect:/toLogin";
        }

    }

    /**
     * Order detail
     */
    @RequestMapping("/orderDetail")
    public String orderDetail(@Param("orderId") String orderId,ModelMap map,HttpServletRequest httpServletRequest) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        map.addAttribute("userId", session.getAttribute("userId"));

        session.setAttribute("formerChoice",httpServletRequest.getRequestURI()+"?"+httpServletRequest.getQueryString());

        OrderInfo orderInfo = orderService.queryOrderInfoObject(orderId);
        List<TicketInfo> ticketInfo = ticketService.queryTicketByOrderId(orderId);
        FlightInfo flightInfo = orderService.queryFlightInfoObject(orderInfo.getFlightNum(), orderInfo.getDepDate());

        map.addAttribute("orderInfo", orderInfo);
        map.addAttribute("ticketInfo", ticketInfo);
        map.addAttribute("flightInfo", flightInfo);

        String depAirportInfo = ticketService.queryDepartureAirport(flightInfo.getDeparture());
        String desAirportInfo = ticketService.queryDestinationAirport(flightInfo.getDestination());
        map.addAttribute("departureAirport", depAirportInfo);
        map.addAttribute("destinationAirport", desAirportInfo);

        System.out.println(ticketInfo);

        return "orderDetail";

    }

    @RequestMapping("/orderCancel")
    public String orderCancel(String orderId) {

        int orderStat = 2;

        orderService.alterOrderStat(orderId,orderStat);

        ticketService.alterTicketStautsByOrderId(orderId, 0);

        return "redirect:/orderList";

    }

    @RequestMapping("/orderDelete")
    public String orderDelete(String orderId) {
        orderService.deleteOrderById(orderId);
        ticketService.eraseCanceledTicket(orderId);
        return "redirect:/orderList";
    }

}

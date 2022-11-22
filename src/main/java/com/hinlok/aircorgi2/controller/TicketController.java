package com.hinlok.aircorgi2.controller;

import com.hinlok.aircorgi2.model.FlightInfo;
import com.hinlok.aircorgi2.model.OrderInfo;
import com.hinlok.aircorgi2.model.TicketInfo;
import com.hinlok.aircorgi2.service.OrderService;
import com.hinlok.aircorgi2.service.TicketService;
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
import java.util.*;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/toquery";
    }

    @RequestMapping("/toquery")
    public String toQuery(ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "bookticket";
        } else {
            return "bookticket";
        }


    }

    @RequestMapping("/queryflight")
    public String queryFlight(HttpServletRequest httpServletRequest, @Param("departure")  String departure, @Param("destination") String destination , @Param("depDate") String depDate, ModelMap map) {

        String depAirportInfo = ticketService.queryDepartureAirport(departure);
        String desAirportInfo = ticketService.queryDestinationAirport(destination);


        map.addAttribute("departureAirport", depAirportInfo);
        map.addAttribute("destinationAirport", desAirportInfo);
        map.addAttribute("selectedDate", depDate);

        List<FlightInfo> queryResult = ticketService.queryFlightByDesDepDate(departure,destination,depDate);

        map.addAttribute("queryResult", queryResult);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        session.setAttribute("exPath",httpServletRequest.getServletPath());

        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "afterquery";
        } else {
            return "afterquery";
        }

    }

    @RequestMapping("/createTicket")
    public String createTicket(@Param("orderId") String  orderId) {

        OrderInfo orderInfo = orderService.queryOrderInfoObject(orderId);

        if (orderInfo.getPassenger1().length() != 0) {
            TicketInfo ticket = new TicketInfo();
            ticket.setOrderId(orderInfo.getOrderId());
            ticket.setPassengerId(orderInfo.getPassenger1());
            ticket.setTicketId(createTicketId(orderInfo.getOrderId()));
            ticket.setFlightNum(orderInfo.getFlightNum());
            ticket.setDepDate(orderInfo.getDepDate());
            ticket.setSeatType(orderInfo.getSeatType());
            ticket.setTicketStatus(1);
            ticket.setPassengerName(ticketService.queryPassengerNameByPassengerId(orderInfo.getPassenger1()));

            ticketService.createTicketByTicketInfo(ticket);
            System.out.println("Ticket1 created.");
        } else {
            System.out.println("passenger1 empty");
        }
        if (orderInfo.getPassenger2().length() != 0) {
            TicketInfo ticket = new TicketInfo();
            ticket.setOrderId(orderInfo.getOrderId());
            ticket.setPassengerId(orderInfo.getPassenger2());
            ticket.setTicketId(createTicketId(orderInfo.getOrderId()));
            ticket.setFlightNum(orderInfo.getFlightNum());
            ticket.setDepDate(orderInfo.getDepDate());
            ticket.setSeatType(orderInfo.getSeatType());
            ticket.setTicketStatus(1);
            ticket.setPassengerName(ticketService.queryPassengerNameByPassengerId(orderInfo.getPassenger2()));

            ticketService.createTicketByTicketInfo(ticket);
            System.out.println("Ticket2 created.");
        } else {
            System.out.println("passenger2 empty");
        }
        if (orderInfo.getPassenger3().length() != 0) {
            TicketInfo ticket = new TicketInfo();
            ticket.setOrderId(orderInfo.getOrderId());
            ticket.setPassengerId(orderInfo.getPassenger3());
            ticket.setTicketId(createTicketId(orderInfo.getOrderId()));
            ticket.setFlightNum(orderInfo.getFlightNum());
            ticket.setDepDate(orderInfo.getDepDate());
            ticket.setSeatType(orderInfo.getSeatType());
            ticket.setTicketStatus(1);
            ticket.setPassengerName(ticketService.queryPassengerNameByPassengerId(orderInfo.getPassenger3()));

            ticketService.createTicketByTicketInfo(ticket);
            System.out.println("Ticket3 created.");
        } else {
            System.out.println("passenger3 empty");
        }


        return "redirect:/orderList";

    }


    public String createTicketId(String orderId) {

        Random random = new Random();

        return orderId.substring(0,10) + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
    }

    @RequestMapping("/ticketCancel")
    public String ticketCancel(String ticketId) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        ticketService.alterTicketStauts(ticketId,0);

        return "redirect:"+session.getAttribute("formerChoice");
    }




}

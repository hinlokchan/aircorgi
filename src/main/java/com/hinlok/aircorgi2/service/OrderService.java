package com.hinlok.aircorgi2.service;

import com.hinlok.aircorgi2.model.FlightInfo;
import com.hinlok.aircorgi2.model.MultiQuery;
import com.hinlok.aircorgi2.model.OrderInfo;
import com.hinlok.aircorgi2.model.PassengerInfo;


import java.util.List;

public interface OrderService {

    List<MultiQuery> flightDetail(String flightNum,String depDate);


    String selectDepByFlight(String flightNum);
    String selectDesByFlight(String flightNum);

    List<PassengerInfo> queryPassengerInfo(String userId);


    int calculateBill(OrderInfo orderInfo);

    int createOrder(String orderId,String dateString,int priceAmount,String userId,int ticketAmount,int orderStat);

    int appendOrder(OrderInfo orderInfo,String orderId);

    List<OrderInfo> queryOrderInfo(String orderId);

    String alterOrderStat(String orderId, int orderStat);

    List<OrderInfo> queryAllOrder(String userId);

    OrderInfo queryOrderInfoObject(String orderId);

    FlightInfo queryFlightInfoObject(String flightNum, String depDate);

    int deleteOrderById(String orderId);

}

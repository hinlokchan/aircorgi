package com.hinlok.aircorgi2.service.impl;

import com.hinlok.aircorgi2.mapper.*;
import com.hinlok.aircorgi2.model.*;
import com.hinlok.aircorgi2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MultiQueryMapper multiQueryMapper;

    @Autowired
    private FlightInfoMapper flightInfoMapper;

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    @Autowired
    private PriceInfoMapper priceInfoMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<MultiQuery> flightDetail(String flightNum, String depDate) {
        return multiQueryMapper.flightDetail(flightNum,depDate);
    }

    @Override
    public String selectDepByFlight(String flightNum) {
        return flightInfoMapper.selectDepByFlight(flightNum);
    }

    @Override
    public String selectDesByFlight(String flightNum) {
        return flightInfoMapper.selectDesByFlight(flightNum);
    }

    @Override
    public List<PassengerInfo> queryPassengerInfo(String userId) {
        return passengerInfoMapper.selectAllPassenger(userId);
    }



    @Override
    public int calculateBill(OrderInfo orderInfo) {
        return priceInfoMapper.selectPriceBySeatType(orderInfo);
    }

    @Override
    public int createOrder(String orderId, String dateString, int priceAmount, String userId,int ticketAmount,int orderStat) {
        orderInfoMapper.createOrderByOrderId(orderId, dateString, priceAmount, userId,ticketAmount,orderStat);
        return 0;
    }

    @Override
    public int appendOrder(OrderInfo orderInfo,String orderId) {
        orderInfoMapper.updateOrderByOrderId(orderInfo,orderId);
        return 0;
    }

    @Override
    public List<OrderInfo> queryOrderInfo(String orderId) {
        return orderInfoMapper.selectOrderInfoByOrderId(orderId);
    }

    @Override
    public String alterOrderStat(String orderId, int orderStat) {
        orderInfoMapper.updateOrderStatByOrderId(orderId, orderStat);
        return null;
    }

    @Override
    public List<OrderInfo> queryAllOrder(String userId) {
        return orderInfoMapper.selectAllOrderByUserId(userId);
    }

    @Override
    public OrderInfo queryOrderInfoObject(String orderId) {
        return orderInfoMapper.selectOrderInfoObjectByOrderId(orderId);
    }

    @Override
    public FlightInfo queryFlightInfoObject(String flightNum, String depDate) {
        return flightInfoMapper.selectFlightInfoByFlightNumDepDate(flightNum,depDate);
    }

    @Override
    public int deleteOrderById(String orderId) {
        orderInfoMapper.deleteByPrimaryKey(orderId);
        return 0;
    }
}

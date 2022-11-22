package com.hinlok.aircorgi2.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {
    private String orderId;

    private String userId;

    private String createDatetime;

    private String flightNum;

    private String depDate;

    private Integer ticketAmount;

    private String seatType;

    private Integer priceAmount;

    private String passenger1;
    private String passenger2;
    private String passenger3;

    private Integer orderStat;

    public Integer getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(Integer orderStat) {
        this.orderStat = orderStat;
    }

    public String getPassenger1() {
        return passenger1;
    }

    public void setPassenger1(String passenger1) {
        this.passenger1 = passenger1;
    }

    public String getPassenger2() {
        return passenger2;
    }

    public void setPassenger2(String passenger2) {
        this.passenger2 = passenger2;
    }

    public String getPassenger3() {
        return passenger3;
    }

    public void setPassenger3(String passenger3) {
        this.passenger3 = passenger3;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Integer priceAmount) {
        this.priceAmount = priceAmount;
    }
}
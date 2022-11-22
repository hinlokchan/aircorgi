package com.hinlok.aircorgi2.model;

import java.util.Date;

public class MultiQuery {
    private String flightNum;

    private String depDate;

    private String fclassPrice;

    private String bclassPrice;

    private String eclassPrice;

    private String depTime;

    private String arrTime;

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

    public String getFclassPrice() {
        return fclassPrice;
    }

    public void setFclassPrice(String fclassPrice) {
        this.fclassPrice = fclassPrice;
    }

    public String getBclassPrice() {
        return bclassPrice;
    }

    public void setBclassPrice(String bclassPrice) {
        this.bclassPrice = bclassPrice;
    }

    public String getEclassPrice() {
        return eclassPrice;
    }

    public void setEclassPrice(String eclassPrice) {
        this.eclassPrice = eclassPrice;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }
}
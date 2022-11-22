package com.hinlok.aircorgi2.model;

import java.util.Date;

public class PriceInfo {
    private String flightNum;

    private Date depDate;

    private Integer fclassPrice;

    private Integer bclassPrice;

    private Integer eclassPrice;

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Integer getFclassPrice() {
        return fclassPrice;
    }

    public void setFclassPrice(Integer fclassPrice) {
        this.fclassPrice = fclassPrice;
    }

    public Integer getBclassPrice() {
        return bclassPrice;
    }

    public void setBclassPrice(Integer bclassPrice) {
        this.bclassPrice = bclassPrice;
    }

    public Integer getEclassPrice() {
        return eclassPrice;
    }

    public void setEclassPrice(Integer eclassPrice) {
        this.eclassPrice = eclassPrice;
    }
}
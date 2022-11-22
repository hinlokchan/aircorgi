package com.hinlok.aircorgi2.model;

import java.util.Date;

public class TicketRemain {
    private String flightNum;

    private Date depDate;

    private Integer fclassRemain;

    private Integer bclassRemain;

    private Integer eclassRemain;

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

    public Integer getFclassRemain() {
        return fclassRemain;
    }

    public void setFclassRemain(Integer fclassRemain) {
        this.fclassRemain = fclassRemain;
    }

    public Integer getBclassRemain() {
        return bclassRemain;
    }

    public void setBclassRemain(Integer bclassRemain) {
        this.bclassRemain = bclassRemain;
    }

    public Integer getEclassRemain() {
        return eclassRemain;
    }

    public void setEclassRemain(Integer eclassRemain) {
        this.eclassRemain = eclassRemain;
    }
}
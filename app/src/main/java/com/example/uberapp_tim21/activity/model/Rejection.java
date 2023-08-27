package com.example.uberapp_tim21.activity.model;

import java.util.Date;

public class Rejection {

    private Integer id;

    private String reason;

    private Date timeOfRejection;

    public Rejection(String reason, Date timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(Date timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }
}

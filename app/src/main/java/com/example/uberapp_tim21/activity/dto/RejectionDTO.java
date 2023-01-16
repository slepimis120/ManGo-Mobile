package com.example.uberapp_tim21.activity.dto;

import java.util.Date;

public class RejectionDTO {

    private Integer id;

    private String reason;

    private Date timeOfRejection;

    public RejectionDTO(String reason, Date timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;

    }

    public RejectionDTO(){}

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

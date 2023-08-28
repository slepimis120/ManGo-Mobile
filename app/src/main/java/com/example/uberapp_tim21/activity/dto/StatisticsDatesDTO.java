package com.example.uberapp_tim21.activity.dto;

import java.util.Date;

public class StatisticsDatesDTO {

    private String startDate;

    private String endDate;

    public StatisticsDatesDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public StatisticsDatesDTO() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

package com.example.uberapp_tim21.activity.dto;

import com.example.uberapp_tim21.activity.model.Passenger;

public class RidePassengerDTO {

    private Integer id;

    private String email;

    public RidePassengerDTO(Integer id, String email){
        this.id = id;
        this.email = email;
    }

    public RidePassengerDTO(){}

    public RidePassengerDTO(Passenger passenger){
        this.id = passenger.getId().intValue();
        this.email = passenger.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

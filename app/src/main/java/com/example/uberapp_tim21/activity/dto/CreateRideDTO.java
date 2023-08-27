package com.example.uberapp_tim21.activity.dto;

import java.util.Date;
import java.util.List;

public class CreateRideDTO {

    private List<RidePassengerDTO> passengers;

    private String vehicleType;

    private Boolean babyTransport;

    private Boolean petTransport;

    private List<RideLocationDTO> locations;

    private Date scheduledTime;


    public CreateRideDTO(List<RideLocationDTO> locations, List<RidePassengerDTO> passengers, String vehicleType, Boolean babyTransport, Boolean petTransport, Date scheduledTime){
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.scheduledTime = scheduledTime;
    }

    public CreateRideDTO(){}

    public List<RidePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<RidePassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(Boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public Boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(Boolean petTransport) {
        this.petTransport = petTransport;
    }

    public List<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocationDTO> locations) {
        this.locations = locations;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}

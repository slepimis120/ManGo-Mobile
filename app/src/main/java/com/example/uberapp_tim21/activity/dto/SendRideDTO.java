package com.example.uberapp_tim21.activity.dto;

import java.util.ArrayList;
import java.util.Date;

public class SendRideDTO {
    private ArrayList<UserDTO> passengers;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private ArrayList<RideLocationDTO> locations;

    public SendRideDTO( ArrayList<RideLocationDTO> locations, ArrayList<UserDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport){
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public SendRideDTO(){}


    public ArrayList<UserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<UserDTO> passengers) {
        this.passengers = passengers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public ArrayList<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<RideLocationDTO> locations) {
        this.locations = locations;
    }

}

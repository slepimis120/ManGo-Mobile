package com.example.uberapp_tim21.activity.model;


import com.example.uberapp_tim21.activity.dto.CreateRideDTO;
import com.example.uberapp_tim21.activity.dto.RideLocationDTO;
import com.example.uberapp_tim21.activity.dto.RidePassengerDTO;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ride {

    private int id;
    private Date startTime;
    private Date endTime;
    private Integer totalCost;
    private Driver driver;
    private List<Passenger> passengers;
    private Integer estimatedTimeInMinutes;
    private Vehicle.Type vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private Status status;
    private Date scheduledTime;
    private List<RideLocation> locations = new ArrayList<RideLocation>();
    private Rejection rejection;
    private List<Panic> panic;
    private List<UserMessage> userMessages;
    private List<Review> reviews;

    public enum Status{pending, accepted, rejected, active, finished, cancelled, started}


    public Ride(Driver driver, List<RideLocation> locations, List<Passenger> passengers, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport, Date scheduledTime){
        this.id = 0;
        this.driver = driver;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.startTime = null;
        this.endTime = null;
        this.totalCost = null;
        this.estimatedTimeInMinutes = null;
        this.status = Status.pending;
        this.scheduledTime = scheduledTime;
    }

    public Ride(Driver driver, List<RideLocation> locations, List<Passenger> passengers, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport){
        this.id = 0;
        this.driver = driver;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.startTime = null;
        this.endTime = null;
        this.totalCost = null;
        this.estimatedTimeInMinutes = null;
        this.status = Status.pending;
    }

    public Ride(){}

    public Ride(CreateRideDTO createRideDTO){
        this.driver = null;
        for(RideLocationDTO rideLocationDTO : createRideDTO.getLocations()){
            this.locations.add(new RideLocation(rideLocationDTO));
        }
        this.passengers = new ArrayList<>();
        for(RidePassengerDTO passengerDTO : createRideDTO.getPassengers()){
            this.passengers.add(new Passenger(passengerDTO));
        }
        this.vehicleType = Vehicle.Type.valueOf(createRideDTO.getVehicleType().toUpperCase());
        this.babyTransport = createRideDTO.isBabyTransport();
        this.petTransport = createRideDTO.isPetTransport();
        this.startTime = new Date();
        this.endTime = null;
        this.totalCost = null;
        this.estimatedTimeInMinutes = null;
        this.status = Status.pending;
        this.scheduledTime = createRideDTO.getScheduledTime();
    }


    public List<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocation> locations) {
        this.locations = locations;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int rideId) {
        this.id = rideId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rejection getRejection() {
        return rejection;
    }

    public void setRejection(Rejection rejection) {
        this.rejection = rejection;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}

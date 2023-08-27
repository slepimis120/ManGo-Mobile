package com.example.uberapp_tim21.activity.model;

import com.example.uberapp_tim21.activity.dto.RideLocationDTO;

import java.util.List;

public class RideLocation {

    private int id;
    private Location departure;
    private Location destination;
    private Ride ride;
    private List<FavoriteLocations> favoriteLocations;

    public RideLocation(Location departure, Location destination, Ride ride) {
        this.departure = departure;
        this.destination = destination;
        this.ride = ride;
    }

    public RideLocation(Location departure, Location destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public RideLocation(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public RideLocation(RideLocationDTO rideLocationDTO){
        this.destination = new Location(rideLocationDTO.getDestination());
        this.departure = new Location(rideLocationDTO.getDeparture());
    }
}

package com.example.uberapp_tim21.activity.dto;

public class RideLocationDTO {


    private LocationDTO departure;

    private LocationDTO destination;


    public RideLocationDTO(LocationDTO departure, LocationDTO destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public RideLocationDTO(){}

    public LocationDTO getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO departure) {
        this.departure = departure;
    }

    public LocationDTO getDestination() {
        return destination;
    }

    public void setDestination(LocationDTO destination) {
        this.destination = destination;
    }

}

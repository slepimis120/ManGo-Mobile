package com.example.uberapp_tim21.activity.model;

import com.example.uberapp_tim21.activity.dto.LocationDTO;

public class Location {

    private Integer id;
    private String address;
    private Float latitude;
    private Float longitude;


    public Location(String address, Float latitude, Float longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(LocationDTO locationDTO){
        this.address = locationDTO.getAddress();
        this.latitude = locationDTO.getLatitude();
        this.longitude = locationDTO.getLongitude();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Location() {

    }
}

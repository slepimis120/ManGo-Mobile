package com.example.uberapp_tim21.activity.model;

public class Review {

    private Integer id;
    private Integer rating;
    private String comment;
    private Review.Type reviewType;
    private Passenger passengers;

    public Review(Integer ride, Integer rating, String comment, Type reviewType) {
        this.rating = rating;
        this.comment = comment;
        this.reviewType = reviewType;
    }

    public Review(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Type getReviewType() {
        return reviewType;
    }

    public void setReviewType(Type reviewType) {
        this.reviewType = reviewType;
    }

    public Passenger getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger passengers) {
        this.passengers = passengers;
    }

    public enum Type{
        VEHICLE, DRIVER
    }
}

package com.example.uberapp_tim21.activity.model;

import java.util.Date;

public class Activation {

    private Integer id;
    private Passenger passengerId;
    private Date activationSendDate;
    private boolean isActivated;

    public Activation(Passenger passengerId, Date activationSendDate, boolean isActivated) {
        this.passengerId = passengerId;
        this.activationSendDate = activationSendDate;
        this.isActivated = isActivated;
    }

    public Activation(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Passenger getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Passenger passengerId) {
        this.passengerId = passengerId;
    }

    public Date getActivationSendDate() {
        return activationSendDate;
    }

    public void setActivationSendDate(Date activationSendDate) {
        this.activationSendDate = activationSendDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}

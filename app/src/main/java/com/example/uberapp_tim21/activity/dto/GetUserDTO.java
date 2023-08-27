package com.example.uberapp_tim21.activity.dto;

import com.example.uberapp_tim21.activity.model.User;

public class GetUserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;

    public GetUserDTO (Integer id, String name, String surname, String profilePicture,
                    String telephoneNumber, String email, String address){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }


    public GetUserDTO(User user) {
        this.id = user.getId().intValue();
        this.name = user.getName();
        this.surname = user.getLastName();
        this.profilePicture = user.getProfilePhoto();
        this.telephoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }

    public GetUserDTO(Object[] object){
        this.id = Integer.getInteger( (String) object[2]) ;
        this.address = (String) object[3];
        this.email = (String)object[5];
        this.name = (String)object[6];
        this.profilePicture = (String)object[7];
        this.surname = (String)object[8];
        this.telephoneNumber = (String)object[9];
    }

    public GetUserDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

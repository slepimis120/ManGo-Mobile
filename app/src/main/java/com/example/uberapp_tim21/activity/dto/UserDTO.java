package com.example.uberapp_tim21.activity.dto;

public class UserDTO {

    private Integer id;

    private String email;

    public UserDTO(Integer id, String email){
        this.id = id;
        this.email = email;
    }

    public UserDTO(){}

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

package com.example.uberapp_tim21.activity.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.uberapp_tim21.R;

import java.util.Date;

public class PassengerInbox {
    private String sender;
    private String content;
    private Date sendDate;
    private MessageType type;
    private Integer id;
    private int avatar;

    public PassengerInbox(){

    }

    public PassengerInbox(String sender, Date sendDate, MessageType type, Integer id, String content, int avatar){
        this.sender = sender;
        this.sendDate = sendDate;
        this.type = type;
        this.id = id;
        this.content = content;
        this.avatar = avatar;
    }

    public String getSender(){return sender;}

    public Date getSendDate(){return sendDate;}

    public MessageType getType(){return type;}

    public Integer getId(){return id;}

    public String getContent(){return content;}

    public int getAvatar(){return avatar;}

    public void setSender(String sender){this.sender = sender;}

    public void setSendDate(Date sendDate){this.sendDate = sendDate;}

    public void setType(MessageType type){this.type = type;}

    public void setId(Integer id){this.id = id;}

    public void setContent(String content){this.content = content;}

    public void setAvatar(int avatar){this.avatar = avatar;}

}
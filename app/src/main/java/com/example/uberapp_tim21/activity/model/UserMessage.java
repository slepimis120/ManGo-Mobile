package com.example.uberapp_tim21.activity.model;


import java.time.LocalDateTime;

public class UserMessage {

	private Integer id;
	private LocalDateTime timeOfSending;
	private User sender;

	private User receiver;
	private String message;
	private Type type;

	public enum Type{
		SUPPORT, RIDE, PANIC
	}

	public UserMessage(Integer id, LocalDateTime timeOfSending, Integer senderId, Integer receiverId, String message,
			Type type) {
		super();
		this.id = id;
		this.timeOfSending = timeOfSending;
		this.sender.setId(senderId.longValue());
		this.receiver.setId(receiverId.longValue());
		this.message = message;
		this.type = type;
	}
	public UserMessage() {
	}

	public UserMessage(LocalDateTime timeOfSending, User sender, User receiver, String message, Type type) {
		this.timeOfSending = timeOfSending;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getTimeOfSending() {
		return timeOfSending;
	}
	public void setTimeOfSending(LocalDateTime timeOfSending) {
		this.timeOfSending = timeOfSending;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
}

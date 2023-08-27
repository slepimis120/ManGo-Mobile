package com.example.uberapp_tim21.activity.model;

import com.example.uberapp_tim21.activity.dto.ExpandedUserDTO;
import com.example.uberapp_tim21.activity.dto.RidePassengerDTO;

import java.util.List;

public class Passenger extends User{

	private List<Ride> rides;
	private List <Review> reviews;
	private List<FavoriteLocations> favoriteLocations;
	private Activation activation;

	public Passenger(Long id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(id, firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
	}

	public Passenger(User user){
		super(user.getId(), user.getName(), user.getLastName(), user.getProfilePhoto(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getPassword(), user.isBlocked());
	}
	
	public Passenger(ExpandedUserDTO data) {
		super(data);
	}

    public Passenger(RidePassengerDTO passengerDTO) {
		this.setId(passengerDTO.getId().longValue());
		this.setEmail(passengerDTO.getEmail());
    }

    public String getName() {
		return super.getName();
	}

	public Passenger(){}

	

	
	

}

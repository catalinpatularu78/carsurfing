package com.tus.ride.dto;

import com.tus.ride.model.Ride;

public class RideInfo {

	public RideInfo(Ride ride, User user) {
		super();
		this.ride = ride;
		this.user = user;
	}

	private Ride ride;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

}

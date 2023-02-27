package com.tus.ride.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer driverId;
	private String fromLocation;
	private String toLocation;
	private String dateOfDeparture;
	private String estimatedDepartureTime;
	private String stop_1;
	private String stop_2;
	private String stop_3;
	private int spacesLeft;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(String dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public String getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(String estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}

	public String getStop1() {
		return stop_1;
	}

	public void setStop1(String stop_1) {
		this.stop_1 = stop_1;
	}

	public String getStop2() {
		return stop_2;
	}

	public void setStop2(String stop_2) {
		this.stop_2 = stop_2;
	}

	public String getStop3() {
		return stop_3;
	}

	public void setStop3(String stop_3) {
		this.stop_3 = stop_3;
	}

	public int getSpacesLeft() {
		return spacesLeft;
	}

	public void setSpacesLeft(int spacesLeft) {
		this.spacesLeft = spacesLeft;
	}
}

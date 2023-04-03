package com.tus.ride;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.ride.model.Ride;
import com.tus.ride.repo.RideRepository;

@Service
public class RideService {

	@Autowired
	private RideRepository rideRepo;

	public void deleteExpiredRides(String date) {
		List<Ride> rides = rideRepo.findByDateOfDeparture(date);
		if (rides.size() != 0) {
			for (Ride ride : rides) {
				rideRepo.deleteById(ride.getId());
			}
		}
	}

}

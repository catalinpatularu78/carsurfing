package com.tus.ride.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tus.ride.model.Booking;
import com.tus.ride.model.Ride;
import com.tus.ride.repo.BookingRepository;
import com.tus.ride.repo.RideRepository;
import com.tus.ride.response.ErrorResponse;
import com.tus.ride.response.Response;
import com.tus.ride.response.ResponseCode;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@Transactional
@Service
@RequestMapping("/rideapi")
public class RideRestController {

	Logger logger = LoggerFactory.getLogger(RideRestController.class);

	@Autowired
	private RideRepository rideRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@GetMapping(value = "/rides")
	List<Ride> get() {
		return rideRepo.findAll();
	}

	@GetMapping("/rides/{id}")
	public ResponseEntity<?> getRideById(@PathVariable(value = "id") Integer rideId) {
		Optional<Ride> ride = rideRepo.findById(rideId);
		if (ride.isPresent())
			return ResponseEntity.ok().body(ride.get());
		else
			return ResponseEntity.ok().body("Ride not found");
	}

	@PostMapping(value = "/rides")
	Response addRide(@RequestBody Ride ride) {
		ResponseCode responseCode;
		String responseMessage = "";
		try {
			rideRepo.save(ride);
			responseCode = ResponseCode.SUCCESS;
			responseMessage = "Ride from location " + ride.getFromLocation() + " to destination " + ride.getToLocation()
					+ " added.";
			return new Response(responseCode, responseMessage);

		} catch (Exception exc) {
			responseCode = ResponseCode.FAILED;
			responseMessage = "Ride from location " + ride.getFromLocation() + " to destination " + ride.getToLocation()
					+ " cannot be added.";
			return new ErrorResponse(responseCode, responseMessage, exc.getCause().getCause().getMessage());
		}
	}

	@DeleteMapping(value = "/rides/{id}")
	Response deleteRide(@PathVariable("id") Integer id) {
		ResponseCode responseCode;
		String responseMessage = "";

		try {
			Optional<Ride> existingRide = rideRepo.findById(id);
			rideRepo.delete(existingRide.get());
			responseCode = ResponseCode.SUCCESS;
			responseMessage = "Ride from location " + existingRide.get().getFromLocation() + " to destination "
					+ existingRide.get().getToLocation() + " deleted.";
			return new Response(responseCode, responseMessage);

		} catch (Exception exc) {
			responseCode = ResponseCode.FAILED;
			responseMessage = "Ride cannot be deleted.";
			return new ErrorResponse(responseCode, responseMessage, exc.getCause().getCause().getMessage());
		}
	}

	@RequestMapping(value = "/ridesForRouteAndDate", method = RequestMethod.POST)
	public List<Ride> findRidePerRouteAndDate(@RequestBody Map<String, String> rideRequest) {
		List<Ride> rides = new ArrayList<Ride>();
		if (!rideRequest.get("fromLocation").equalsIgnoreCase(rideRequest.get("toLocation"))) {
			List<Ride> existingRides = rideRepo.findRides(rideRequest.get("fromLocation"),
					rideRequest.get("toLocation"), rideRequest.get("dateOfDeparture"));
			if (!existingRides.isEmpty()) {
				rides = getRidesWithSpaces(existingRides);
			}

		}
		return rides;
	}

	@GetMapping("rides/{fromLocation}/{toLocation}")
	public List<Ride> findByLocation(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation) {
		List<Ride> rides = new ArrayList<Ride>();
		if (!fromLocation.equalsIgnoreCase(toLocation)) {
			List<Ride> existingRides = rideRepo.findByFromLocationAndToLocation(fromLocation, toLocation);
			if (!existingRides.isEmpty()) {
				rides = getRidesWithSpaces(existingRides);
			}
		}
		return rides;

	}

	@Transactional(propagation = Propagation.MANDATORY)
	@PostMapping(value = "/rides/booking")
	Response addBooking(@RequestBody Booking booking) {
		try {
			// check if spaces are still available
			Optional<Ride> existingRide = rideRepo.findById(booking.getRideId());
			if (existingRide.isPresent()) {
				// check if booking already exists
				Optional<Booking> existingBooking = bookingRepo.findByRideIdAndPassengerId(booking.getRideId(),
						booking.getPassengerId());
				if (existingBooking.isPresent()) {
					return new ErrorResponse(ResponseCode.FAILED, "Ride cannot be booked.", "Booking already exists.");
				}
			} else {
				return new ErrorResponse(ResponseCode.FAILED, "Ride cannot be booked.", "Ride doesn't exist.");
			}

			if (existingRide.get().getSpacesLeft() > 0) {
				bookingRepo.save(booking);
			} else {
				return new ErrorResponse(ResponseCode.FAILED, "Ride cannot be booked.", "The spaces are full.");
			}

			// decrease the number of spaces
			int newNumberOfSpaces = existingRide.get().getSpacesLeft() - 1;
			// do number of spaces left -1
			rideRepo.updateNumberOfSpacesLeft(booking.getRideId(), newNumberOfSpaces);
			return new Response(ResponseCode.SUCCESS, "Ride was booked successfully.");

		} catch (Exception exc) {
			return new ErrorResponse(ResponseCode.FAILED, "Ride cannot be booked.", exc.getMessage());
		}

	}

	// get only rides with least 1 space available
	private List<Ride> getRidesWithSpaces(List<Ride> existingRides) {
		List<Ride> rides = new ArrayList<Ride>();
		for (Ride ride : existingRides) {
			if (ride.getSpacesLeft() != 0) {
				rides.add(ride);
			}
		}
		return rides;
	}

}

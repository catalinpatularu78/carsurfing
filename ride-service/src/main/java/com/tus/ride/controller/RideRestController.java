package com.tus.ride.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tus.ride.dto.RideInfo;
import com.tus.ride.dto.User;
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


	@Autowired
	private RestTemplate restTemplate;

	@Value("${userService.url}")
	private String userServiceURL;

	@GetMapping(value = "/rides")
	ResponseEntity<?> get( @RequestHeader("Authorization") String header) {

		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
			 return ResponseEntity.ok().body(rideRepo.findAll());}
		 else {
			 return ResponseEntity
			          .badRequest()
			          .body(new String("Error: User not logged in!"));
		 }
		 
	}

	@GetMapping("/rides/{id}")
	public ResponseEntity<?> getRideById(@PathVariable(value = "id") Integer rideId, @RequestHeader("Authorization") String header) {
		try{
			HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
		
		Optional<Ride> ride = rideRepo.findById(rideId);
		if (ride.isPresent())
			return ResponseEntity.ok().body(ride.get());
		else
			return ResponseEntity.ok().body("Ride not found");
		 }
		 else {
			 return ResponseEntity
			          .badRequest()
			          .body(new String("Error: User not found!"));
		 }
		}catch(Exception e) {
			return ResponseEntity
			          .badRequest()
			          .body(new String("Error!"));
		}
	}

	@PostMapping(value = "/rides")
	Response addRide(@RequestBody Ride ride, @RequestHeader("Authorization") String header) {
		ResponseCode responseCode;
		String responseMessage = "";
		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
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
		}}
		 else {
			 responseCode = ResponseCode.FAILED;
				responseMessage = "Ride from location " + ride.getFromLocation() + " to destination " + ride.getToLocation()
						+ " cannot be added.";
				return new ErrorResponse(responseCode, responseMessage, responseMessage);

		 }
		 
	}

	@DeleteMapping(value = "/rides/{id}")
	Response deleteRide(@PathVariable("id") Integer id, @RequestHeader("Authorization") String header) {
		ResponseCode responseCode;
		String responseMessage = "";
		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
		

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
		}}else {
			responseCode = ResponseCode.FAILED;
			responseMessage = "Ride cannot be deleted.";
			return new ErrorResponse(responseCode, responseMessage, responseMessage);
		}
	}

	@RequestMapping(value = "/ridesForRouteAndDate", method = RequestMethod.POST)

	public ResponseEntity<?> findRidePerRouteAndDate(@RequestBody Map<String, String> rideRequest, @RequestHeader("Authorization") String header) {

		List<Ride> rides = new ArrayList<Ride>();
		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
		if (!rideRequest.get("fromLocation").equalsIgnoreCase(rideRequest.get("toLocation"))) {
			List<Ride> existingRides = rideRepo.findRides(rideRequest.get("fromLocation"),
					rideRequest.get("toLocation"), rideRequest.get("dateOfDeparture"));
			if (!existingRides.isEmpty()) {
				rides = getRidesWithSpaces(existingRides);
			}

		}

		return ResponseEntity.ok().body(rides);
		 }else {
			 return ResponseEntity
			          .badRequest()
			          .body(new String("Error: User not found!"));
		 }
	}

	@GetMapping("rides/{fromLocation}/{toLocation}")
	public ResponseEntity<?> findByLocation(@PathVariable("fromLocation") String fromLocation,
			@PathVariable("toLocation") String toLocation, @RequestHeader("Authorization") String header) {

		List<Ride> rides = new ArrayList<Ride>();
		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
		if (!fromLocation.equalsIgnoreCase(toLocation)) {
			List<Ride> existingRides = rideRepo.findByFromLocationAndToLocation(fromLocation, toLocation);
			if (!existingRides.isEmpty()) {
				rides = getRidesWithSpaces(existingRides);
			}
		}

		return ResponseEntity.ok().body(rides);
		 }else {
			 return ResponseEntity
			          .badRequest()
			          .body(new String("Error: User not found!"));
		 }

	}

	@Transactional(propagation = Propagation.MANDATORY)
	@PostMapping(value = "/rides/booking")
	Response addBooking(@RequestBody Booking booking,  @RequestHeader("Authorization") String header) {
		HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
		
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
		}}else {
			 return new Response(ResponseCode.FAILED, "Ride cannot be booked.");
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

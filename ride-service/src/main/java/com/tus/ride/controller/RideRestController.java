package com.tus.ride.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.ride.model.Ride;
import com.tus.ride.repo.RideRepository;
import com.tus.ride.response.ErrorResponse;
import com.tus.ride.response.Response;
import com.tus.ride.response.ResponseCode;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/rideapi")
public class RideRestController {

	@Autowired
	private RideRepository repo;

	@GetMapping(value = "/rides")
	List<Ride> get() {
		return repo.findAll();
	}

	@PostMapping(value = "/rides")
	Response insertRide(@RequestBody Ride ride) {
		ResponseCode responseCode;
		String responseMessage = "";
		try {
			repo.save(ride);
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
			Optional<Ride> existingRide = repo.findById(id);
			repo.delete(existingRide.get());
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
}

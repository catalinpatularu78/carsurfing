package com.tus.ride.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.ride.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	Optional<Booking> findByRideIdAndPassengerId(int rideId, int passengerId);

}

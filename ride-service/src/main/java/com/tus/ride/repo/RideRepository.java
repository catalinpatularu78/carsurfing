package com.tus.ride.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tus.ride.model.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {

	@Query(value = "select * from ride u where u.fromLocation = :fromLocation", nativeQuery = true)
	Ride findRide(@Param("fromLocation") String fromLocation);

}

package com.tus.ride.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tus.ride.model.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {

	@Query(value = "select * from ride r where r.from_location = :fromLocation and r.to_location=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_1=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_2=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_3=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_2 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_3 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.stop_2=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.stop_3=:toLocation or"
			+ " r.stop_2 = :fromLocation and r.stop_3=:toLocation and"
			+ " r.date_Of_Departure=:dateOfDeparture", nativeQuery = true)
	List<Ride> findRides(@Param("fromLocation") String fromLocation, @Param("toLocation") String toLocation,
			@Param("dateOfDeparture") String dateOfDeparture);

	@Query(value = "select * from ride r where r.from_location = :fromLocation and r.to_location=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_1=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_2=:toLocation or"
			+ " r.from_location = :fromLocation and r.stop_3=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_2 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_3 = :fromLocation and r.to_location=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.stop_2=:toLocation or"
			+ " r.stop_1 = :fromLocation and r.stop_3=:toLocation or"
			+ " r.stop_2 = :fromLocation and r.stop_3=:toLocation ", nativeQuery = true)
	List<Ride> findByFromLocationAndToLocation(String fromLocation, String toLocation);

	@Modifying
	@Transactional
	@Query(value = "UPDATE ride r SET r.spaces_Left = :spacesLeft WHERE r.id =:id", nativeQuery = true)
	void updateNumberOfSpacesLeft(int id, int spacesLeft);

	Optional<Ride> findById(Long rideId);

	List<Ride> findByDateOfDeparture(String dateOfDeparture);

	@Transactional
	@Modifying
	@Query(value = "delete from ride r where STR_TO_DATE(r.date_of_departure, '%Y-%m-%d') < STR_TO_DATE('2022-10-24',  '%Y-%m-%d')", nativeQuery = true)
	void deleteRidesWithOldDateOfDeparture(String date);

	List<Ride> findByDriverId(int driverId);

}

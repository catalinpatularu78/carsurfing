package com.tus.ride.housekeeping;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tus.ride.RideService;
import com.tus.ride.repo.RideRepository;

@Component
public class RideHousekeepingScheduler {
	private static final Logger log = LoggerFactory.getLogger(RideHousekeepingScheduler.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private RideService rideService;

	@Autowired
	private RideRepository rideRepo;

	@Transactional
	@Scheduled(cron = "0 0 6,19 * * *")
	public void reportCurrentTime() {
		String date = dateFormat.format(new Date());
		log.info("The date is now {}", date);

		rideRepo.deleteRidesWithOldDateOfDeparture(date);

	}

}

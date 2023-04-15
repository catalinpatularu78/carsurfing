package com.tus.review.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tus.review.model.User;
import com.tus.review.model.Review;
import com.tus.review.repos.ReviewRepo;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reviewapi")
public class ReviewRestController {

	@Autowired
	private ReviewRepo repo;

	@Value("${userService.url}")
	private String userServiceURL;
	
	@GetMapping("/reviews/{reviewedId}")
	ResponseEntity<?> getReviewQueryId(@PathVariable("reviewedId") Integer reviewedId, @RequestHeader("Authorization") String header) {
		try{
			HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "generalauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
			List<Review> reviews=repo.findByReviewedId(reviewedId);
			return ResponseEntity.ok(reviews);
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
	@RequestMapping(value = "/reviews/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> create(@PathVariable("id") Long id, @RequestHeader("Authorization") String header) {
		try{
			HttpHeaders headers = new HttpHeaders();
		
		 headers.set("Authorization",header);
		 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Boolean> responseObj = restTemplate.exchange(userServiceURL + "adminauth", HttpMethod.GET, entity, Boolean.class);
		 Boolean pass = responseObj.getBody();
		 if (pass) {
			 Optional <Review> review=repo.findById(id);
			 repo.deleteById(id);
			 return ResponseEntity.ok(review);
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

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public ResponseEntity<?>  create(@RequestBody Review review, @RequestHeader("Authorization") String header) {

		try {
			//User user = restTemplate.getForObject(userServiceURL + review.getReviewedId(), User.class);
			 HttpHeaders headers = new HttpHeaders();
			 headers.set("Authorization",header);
			 HttpEntity<String> entity = new HttpEntity<>("paramters",headers);

			 RestTemplate restTemplate = new RestTemplate();
			 ResponseEntity<User> responseObj = restTemplate.exchange(userServiceURL + review.getReviewedId(), HttpMethod.GET, entity, User.class);
			 User user = responseObj.getBody();
			
			if (user != null) {
				repo.save(review);
				HttpHeaders headers2 = new HttpHeaders();
				headers2.set("Authorization",header);
				List<Review> reviews= repo.findByReviewedId(review.getReviewedId());
				Double reviewAverage=0.0;
				for (Review reviewI : reviews) {
					reviewAverage+=reviewI.getRating();
				}
				reviewAverage=reviewAverage/reviews.size();
				HttpEntity<Double> entityPost = new HttpEntity<>(reviewAverage,headers2);

				RestTemplate restTemplatePost = new RestTemplate();
				ResponseEntity<Double> responseObjPost = restTemplatePost.exchange(userServiceURL + "editrating/" +review.getReviewedId(), HttpMethod.PUT, entityPost, Double.class);
				responseObjPost.getBody();
				return ResponseEntity.ok(new String("Review added successfully!"));

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

}

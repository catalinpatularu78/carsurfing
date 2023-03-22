package com.tus.review.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tus.review.model.User;
import com.tus.review.model.Review;
import com.tus.review.repos.ReviewRepo;


@RestController
@RequestMapping("/reviewapi")
public class ReviewRestController {

	@Autowired
	private ReviewRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${userService.url}")
	private String userServiceURL;
	
	@GetMapping("/reviews/{reviewedId}")
	List<Review> getReviewQueryId(@PathVariable("reviewedId") Integer reviewedId) {
		List<Review> reviews=repo.findByReviewedId(reviewedId);
		return reviews;
	}
	@RequestMapping(value = "/reviews/{id}", method = RequestMethod.DELETE)
	public Optional <Review> create(@PathVariable("id") Long id) {
		Optional <Review> review=repo.findById(id);
		repo.deleteById(id);
		return review;

	}

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public Review create(@RequestBody Review review) {
		Review review2= new Review();
		try {
			User user = restTemplate.getForObject(userServiceURL + review.getReviewedId(), User.class);
			
			review2.setComment("INVALID USER");
			if (user != null) {
				return repo.save(review);
			}
			return review2;
		}catch(Exception e) {
			review2.setComment(e.getMessage());
			return review2;
		}
		

	}

}

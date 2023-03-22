package com.tus.review.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.review.model.Review;


public interface ReviewRepo  extends JpaRepository<Review, Long> {
	List<Review> findByReviewedId(Integer reviewedId);
	Optional<Review> findById(int id);

}

package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Transactional
	public void create(ReviewRegisterForm reviewRegisterForm, User user) {
		Review review = new Review();
		
		review.setHouseId(reviewRegisterForm.getHouseId());
		review.setRating(reviewRegisterForm.getRating());
		review.setContent(reviewRegisterForm.getContent());
		review.setName(user.getName());
		
		reviewRepository.save(review);
	}
}

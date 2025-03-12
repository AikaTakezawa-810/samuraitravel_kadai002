package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
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
		Review review = reviewRepository.getReferenceById(reviewRegisterForm.getHouseId());
		review.setHouseId(reviewRegisterForm.getHouseId());
		review.setRating(reviewRegisterForm.getRating());
		review.setContent(reviewRegisterForm.getContent());
		review.setName(user.getName());
		
		reviewRepository.save(review);
	}
	
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getHouseId());
		
		review.setHouseId(reviewEditForm.getHouseId());
		review.setRating(reviewEditForm.getRating());
		review.setContent(reviewEditForm.getContent());
		
		reviewRepository.save(review);
	}
}

package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/houses")
public class AllReviewController {
	private ReviewRepository reviewRepository;
	
	public AllReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@GetMapping("/{id}/review")
	public String index(@PathVariable(name ="id") Integer id,Model model) {
		List<Review> review = reviewRepository.findTop6ByHouseIdOrderByCreatedAtDesc(id);
		model.addAttribute("review", review);
		return "review/index";
	}
}

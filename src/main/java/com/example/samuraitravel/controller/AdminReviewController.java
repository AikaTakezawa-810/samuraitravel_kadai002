package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/review")
public class AdminReviewController {
	private final ReviewRepository reviewRepository;
	
	public AdminReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Review> review = reviewRepository.findAll();
		model.addAttribute("review", review);
		return "/review/index";
	}
	
	@GetMapping("/houses/{id}/review")
	public String getReviewPage(@PathVariable Integer id, Model model, @RequestParam(defaultValue = "0") int page) {
	    List<Review> reviewPage = reviewRepository.findByHouseId(id, PageRequest.of(page, 10));
	    model.addAttribute("reviewPage", reviewPage);
	    return "review/index";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getName(), review.getRating(), review.getContent());
		
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "admin/review/houses/edit";
	}
}

package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/review")
public class AdminReviewController {
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	
	@Autowired
	public AdminReviewController(ReviewRepository reviewRepository, HouseRepository houseRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Review> review = reviewRepository.findAll();
		model.addAttribute("review", review);
		return "/review/index";
	}
	
	@GetMapping("/houses/{id}/review")
	public String getReviewPage(@PathVariable("id") Integer id, Model model, @RequestParam(defaultValue = "0") int page) {
	    List<Review> reviewPage = reviewRepository.findTop6ByHouseIdOrderByCreatedAtDesc(id);
	    model.addAttribute("reviewPage", reviewPage);
	    House house = houseRepository.findById(id).orElse(null);
	    model.addAttribute("house", house);
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

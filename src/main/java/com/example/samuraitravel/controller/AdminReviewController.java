package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class AdminReviewController {
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	private final ReviewService reviewService;
	
	@Autowired
	public AdminReviewController(ReviewRepository reviewRepository, HouseRepository houseRepository, ReviewService reviewService) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Review> review = reviewRepository.findAll();
		model.addAttribute("review", review);
		return "/review/index";
	}
		
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getName(), review.getRating(), review.getContent());
		
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "admin/review/houses/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/houses/edit";
		}
		
		reviewService.update(reviewEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
		
		return "redirect:/admin/review";
	}
}

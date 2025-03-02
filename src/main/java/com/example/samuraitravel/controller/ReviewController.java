package com.example.samuraitravel.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;
	
	public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
		this.reviewService = reviewService;
		this.reviewRepository = reviewRepository;
		}
	
	@GetMapping("/{houseId}/register")
	public String register(@PathVariable(name = "houseId") Integer houseId, Model model) {
		ReviewRegisterForm reviewRegisterForm = new ReviewRegisterForm();
		reviewRegisterForm.setHouseId(houseId);
		model.addAttribute("reviewRegisterForm", reviewRegisterForm);
		return "review/houses/register";
	}
	
	
	@PostMapping("/{houseId}/create")
	public String create(@PathVariable(name = "houseId") Integer houseId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		User user = userDetailsImpl.getUser();
		if (bindingResult.hasErrors()) {
			return "review/houses/register";
		}
		
		reviewRegisterForm.setHouseId(houseId);
		reviewService.create(reviewRegisterForm, user);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
		
		return "redirect:/houses/" + houseId;
	}
		
	@PostMapping("/{houseId}/delete")
	public String delete(@PathVariable(name = "houseId") Integer houseId, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(houseId);
		
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		
		return "redirect:/houses/" + houseId;
	}
}

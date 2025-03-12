package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/houses")
public class AllReviewController {
	private ReviewRepository reviewRepository;
	private HouseRepository houseRepository;
	
	public AllReviewController(ReviewRepository reviewRepository, HouseRepository houseRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
	}

	@GetMapping("/{id}/review")
	public String getReviewPage(@PathVariable("id") Integer id, Model model, @RequestParam(defaultValue = "0") int page, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
	    Page<Review> reviewPage = reviewRepository.findByHouseIdOrderByCreatedAtDesc(id, pageable);
	    model.addAttribute("reviewPage", reviewPage);
	    House house = houseRepository.findById(id).orElse(null);
	    model.addAttribute("house", house);
	    return "review/index";
	}
	
}

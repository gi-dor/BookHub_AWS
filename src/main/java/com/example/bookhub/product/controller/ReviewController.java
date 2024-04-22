package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/product/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public String create(ReviewForm reviewForm, Principal principal){
        long bookNo = reviewForm.getBookNo();

        reviewService.createReview(reviewForm, principal.getName());

        return "redirect:/product/book/detail?bookNo=" + bookNo;
    }

}

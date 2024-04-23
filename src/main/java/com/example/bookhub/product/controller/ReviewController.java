package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.service.ReviewService;
import com.example.bookhub.product.vo.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/{bookNo}")
    @ResponseBody
    public ResponseEntity<List<Review>> getReviews(@PathVariable("bookNo") long bookNo){
        List<Review> reviewList = reviewService.getReviewsByBookNo(bookNo);
        System.out.println(reviewList);
        return ResponseEntity.ok().body(reviewList);
    }

}

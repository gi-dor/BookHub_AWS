package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.dto.ReviewImageDto;
import com.example.bookhub.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/product/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(ReviewForm reviewForm, Principal principal){
        long bookNo = reviewForm.getBookNo();

        reviewService.createReview(reviewForm, principal.getName());

        return "redirect:/product/book/detail?bookNo=" + bookNo;
    }

    @GetMapping("/{bookNo}")
    @ResponseBody
    public ResponseEntity<List<ReviewImageDto>> getReviews(@PathVariable("bookNo") long bookNo){
        List<ReviewImageDto> reviewImageDtoList = reviewService.getReviewsByBookNo(bookNo);
        System.out.println(reviewImageDtoList);
        return ResponseEntity.ok().body(reviewImageDtoList);
    }

    @GetMapping("/recommend/{reviewNo}")
    @ResponseBody
    public ResponseEntity<String> recommend(@PathVariable("reviewNo") long reviewNo, Principal principal){
        String recommendOrNot = reviewService.recommend(reviewNo, principal.getName());
        return ResponseEntity.ok().body(recommendOrNot);
    }

}

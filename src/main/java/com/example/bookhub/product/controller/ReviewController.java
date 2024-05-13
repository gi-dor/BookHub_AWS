package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.dto.ReviewDto;
import com.example.bookhub.product.dto.ReviewReplyForm;
import com.example.bookhub.product.service.ReviewService;
import com.example.bookhub.product.vo.Review;
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
    @PostMapping("/createOrUpdate")
    public String createOrUpdate(ReviewForm reviewForm, Principal principal){
        long bookNo = reviewForm.getBookNo();
        String createYn = reviewForm.getCreateYn();

        long reviewNo = 0;
        if("create".equals(createYn)) {
            reviewNo = reviewService.createReview(reviewForm, principal.getName());
        }
        else if("modify".equals(createYn)) {
            reviewService.modifyReview(reviewForm);
            reviewNo = reviewForm.getReviewNo();
        }
        return String.format("redirect:/product/book/detail?bookNo=%d#review_%d", bookNo, reviewNo);
    }

//    @GetMapping("/{bookNo}")
//    @ResponseBody
//    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("bookNo") long bookNo, Principal principal){
//        List<ReviewDto> reviewDtoList = reviewService.getReviewsByBookNo(bookNo, principal.getName());
//        return ResponseEntity.ok().body(reviewDtoList);
//    }

    @GetMapping("/recommend/{reviewNo}")
    @ResponseBody
    public ResponseEntity<String> recommend(@PathVariable("reviewNo") long reviewNo, Principal principal){
        String recommendOrNot = reviewService.recommend(reviewNo, principal.getName());
        return ResponseEntity.ok().body(recommendOrNot);
    }

    @PostMapping("/reply/create")
    public String createReview(ReviewReplyForm reviewReplyForm, Principal principal){
        long reviewReplyNo = reviewService.createReviewReply(reviewReplyForm, principal.getName());
        return String.format("redirect:/product/book/detail?bookNo=%d#reply_%d", reviewReplyForm.getBookNo(), reviewReplyNo);
    }

    @PostMapping("/reply/modify")
    public String modifyReviewReply(ReviewReplyForm reviewReplyForm){
        reviewService.modifyReviewReply(reviewReplyForm);
        return String.format("redirect:/product/book/detail?bookNo=%d#reply_%d", reviewReplyForm.getBookNo(), reviewReplyForm.getReviewReplyNo());
    }

    @GetMapping("/reply/delete")
    public String deleteReviewReply(@RequestParam("reviewReplyNo") long reviewReplyNo,
                                    @RequestParam("reviewNo") long reviewNo,
                                    @RequestParam("bookNo") long bookNo){
        reviewService.deleteReviewReply(reviewReplyNo, reviewNo);
        return String.format("redirect:/product/book/detail?bookNo=%d#review_%d", bookNo, reviewNo);
    }

    @GetMapping("/modify/{reviewNo}")
    @ResponseBody
    public ResponseEntity<Review> modify(@PathVariable("reviewNo") long reviewNo){
        Review review = reviewService.getReviewByReviewNo(reviewNo);
        return ResponseEntity.ok().body(review);
    }

    @GetMapping("/delete/{bookNo}/{reviewNo}")
    public String deleteReview(@PathVariable("bookNo") long bookNo, @PathVariable("reviewNo") long reviewNo){
        reviewService.deleteReview(reviewNo);
        return String.format("redirect:/product/book/detail?bookNo=%d#review-block", bookNo);
    }

    @GetMapping("/rate/{bookNo}")
    @ResponseBody
    public ResponseEntity<List<Integer>> getRate(@PathVariable("bookNo") long bookNo){
        List<Integer> rateCountList = reviewService.getRate(bookNo);
        return ResponseEntity.ok().body(rateCountList);
    }

    @GetMapping("/reviewTag/{bookNo}")
    @ResponseBody
    public ResponseEntity<List<Integer>> getReviewTagCount(@PathVariable("bookNo") long bookNo){
        List<Integer> reviewTagCountList = reviewService.getReviewTagCount(bookNo);
        return ResponseEntity.ok().body(reviewTagCountList);
    }
}

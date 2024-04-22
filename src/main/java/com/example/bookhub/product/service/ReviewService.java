package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.mapper.ReviewMapper;
import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.Review;
import com.example.bookhub.product.vo.ReviewTag;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;

    public void createReview(ReviewForm reviewForm, String userId) {

        User user = userMapper.selectUserById(userId);

        ReviewTag reviewTag = new ReviewTag();
        reviewTag.setReviewTagNo(reviewForm.getReviewTagNo());

        Book book = new Book();
        book.setBookNo(reviewForm.getBookNo());

        Review review = Review.builder()
                .comment(reviewForm.getComment())
                .rate(reviewForm.getRate())
                .user(user)
                .reviewTag(reviewTag)
                .book(book)
                .build();

        reviewMapper.createReview(review);
        long generatedReviewNo = review.getReviewNo();
        System.out.println("generatedReviewNo: " + generatedReviewNo);
    }
}

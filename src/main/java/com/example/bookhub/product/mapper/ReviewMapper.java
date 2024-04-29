package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.ReviewDto;
import com.example.bookhub.product.vo.Review;
import com.example.bookhub.product.vo.ReviewImage;
import com.example.bookhub.product.vo.ReviewRecommendUser;
import com.example.bookhub.product.vo.ReviewReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    void createReview(Review review);
    List<ReviewDto> getReviewsByBookNo(@Param("bookNo")long bookNo, @Param("userNo") long userNo);
    void createReviewImage(ReviewImage reviewImage);
    Optional<ReviewRecommendUser> getByReviewNoAndUserNo(@Param("reviewNo") long reviewNo, @Param("userNo") long userNo);
    void createReviewRecommendUser(ReviewRecommendUser reviewRecommendUser);
    void deleteReviewRecommendUser(ReviewRecommendUser reviewRecommendUser);
    void updateReviewRecommendCount(@Param("reviewNo") long reviewNo, @Param("recommendStatus") String recommendStatus);
    void createReviewReply(ReviewReply reviewReply);
    Review getReviewByReviewNo(long reviewNo);
    void deleteReviewImageByReviewNo(long reviewNo);
    void updateReview(Review review);
}

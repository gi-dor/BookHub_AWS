package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.ReviewImageDto;
import com.example.bookhub.product.vo.Review;
import com.example.bookhub.product.vo.ReviewImage;
import com.example.bookhub.product.vo.ReviewRecommendUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    void createReview(Review review);
    List<ReviewImageDto> getReviewsByBookNo(long bookNo);
    void createReviewImage(ReviewImage reviewImage);
    Optional<ReviewRecommendUser> getByReviewNoAndUserNo(@Param("reviewNo") long reviewNo, @Param("userNo") long userNo);
    void createReviewRecommendUser(ReviewRecommendUser reviewRecommendUser);
    void deleteReviewRecommendUser(ReviewRecommendUser reviewRecommendUser);
}

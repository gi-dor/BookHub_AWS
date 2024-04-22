package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    void createReview(Review review);
}

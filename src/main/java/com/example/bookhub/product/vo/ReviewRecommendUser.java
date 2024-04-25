package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Data;

@Data
public class ReviewRecommendUser {

    private long reviewRecommendUserNo;
    private Review review;
    private User user;
}

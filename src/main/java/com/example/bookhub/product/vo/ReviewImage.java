package com.example.bookhub.product.vo;

import lombok.Data;

@Data
public class ReviewImage {

    private long reviewImageNo;
    private String imagePath;
    private Review review;
}

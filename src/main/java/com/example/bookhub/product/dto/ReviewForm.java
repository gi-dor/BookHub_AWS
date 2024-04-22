package com.example.bookhub.product.dto;

import lombok.Data;

@Data
public class ReviewForm {

    private long bookNo;
    private float rate;
    private long reviewTagNo;
    private String comment;
    private String imageFiles;
}

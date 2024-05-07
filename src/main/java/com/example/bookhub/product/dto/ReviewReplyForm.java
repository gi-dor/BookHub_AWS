package com.example.bookhub.product.dto;

import lombok.Data;

@Data
public class ReviewReplyForm {

    private long reviewReplyNo;
    private long bookNo;
    private long reviewNo;
    private String comment;
    private int reviewFilter;
}

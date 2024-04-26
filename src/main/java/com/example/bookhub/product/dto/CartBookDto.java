package com.example.bookhub.product.dto;

import lombok.Data;

@Data
public class CartBookDto {

    private Long cartNo;
    private String imageCover;
    private Long bookNo;
    private String name;
    private int price;
    private String cover;
    private float discountRate;
    private int count;
}

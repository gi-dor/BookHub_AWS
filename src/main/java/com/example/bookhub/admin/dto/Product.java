package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long no;
    private long topCategoryNo;
    private long secondCategoryNo;
    private String name;
    private String description;
    private Long publisherNo;
    private String status;
    private int listPrice;
    private double discountRate;
    private int stock;
}

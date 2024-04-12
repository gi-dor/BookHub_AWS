package com.example.bookhub.product.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {

    private Long no;
    private String title;
    private String author;
    private LocalDateTime pubDate;
    private String description;
    private String isbn13;
    private int itemId;
    private int priceSales;
    private int priceStandard;
    private String cover;
    private String categoryId;
    private String categoryName;
    private String publisher;
    private int bestRank;
}

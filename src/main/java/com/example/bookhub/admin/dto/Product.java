package com.example.bookhub.admin.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Product {
    private long no;
    private long categoryNo;
    private long topCategoryNo;
    private long secondCategoryNo;
    private long thirdCategoryNo;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishedDate;
    private String description;
    private long isbn;
    private int pages;
    private String language;
    private int weight;
    private String index;
    private int totalNumber;
    private String size;
    private int height;
    private int width;
    private Long publisherNo;
    private Long authorNo;
    private String status;
    private int listPrice;
    private double discountRate;
    private int stock;
    private int stockNotification;
    private String coverImg;
    private String thumbnailImg;
    private String descriptionImg;
}

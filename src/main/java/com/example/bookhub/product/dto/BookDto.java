package com.example.bookhub.product.dto;

import com.example.bookhub.product.vo.BookAuthor;
import com.example.bookhub.product.vo.Category;
import com.example.bookhub.product.vo.Publisher;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto {

    private long bookNo;
    private String imageCover;
    private String name;
    private Date publishedDate;
    private String description;
    private String isbn;
    private int price;
    private Publisher publisher;
    private Category category;
    private int pages;
    private String language;
    private int weight;
    private int totalNumber;
    private String size;
    private char discontinuingYn;
    private float discountRate;
    private int salesVolume;
    private BookAuthor bookAuthor;
}

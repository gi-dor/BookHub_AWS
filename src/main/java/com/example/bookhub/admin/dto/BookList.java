package com.example.bookhub.admin.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookList {
    private Long no;
    private String image;
    private String bookName;
    private String authorName;
    private int price;
    private float discountRate;
    private int stock;
    private char discontinuingYn;
    private Date publishedDate;
}

package com.example.bookhub.main.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String bookName;
    private String bookPublishedDate;
    private String bookDescription;
    private String isbn;
    private int bookListPrice;
}
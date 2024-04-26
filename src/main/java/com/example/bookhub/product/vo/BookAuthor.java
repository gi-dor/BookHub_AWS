package com.example.bookhub.product.vo;

import lombok.Data;

@Data
public class BookAuthor {
    private Book book;

    // AUTHOR_NO 컬럼
    private Author author;
    private String authorType;

}

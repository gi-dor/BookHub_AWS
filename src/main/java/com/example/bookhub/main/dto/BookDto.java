package com.example.bookhub.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookDto {

    private Long no;
    private String name;
    private String publishedDate;
    private String description;
    private int price;
    private String cover;
    private String publisherName;
    private List<BookAuthorDto> authors;
}

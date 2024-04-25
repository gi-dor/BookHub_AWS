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
    private int listPrice;
    private int sellingPrice;
    private int stock;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private Date publishedDate;
}

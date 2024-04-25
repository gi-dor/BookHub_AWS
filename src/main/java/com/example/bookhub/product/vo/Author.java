package com.example.bookhub.product.vo;

import lombok.Data;

import java.util.Date;
@Data
public class Author {
    private long authorNo;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}

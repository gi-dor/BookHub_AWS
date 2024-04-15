package com.example.bookhub.product.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Publisher {

    private long publisherNo;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}

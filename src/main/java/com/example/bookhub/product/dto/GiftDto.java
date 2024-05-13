package com.example.bookhub.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiftDto {

    private long bookNo;
    private String bookImageCover;
    private String bookName;
    private String authorName;
    private String publisherName;
    private int count;
    private String senderName;
    private String comment;
    private LocalDateTime date;
}

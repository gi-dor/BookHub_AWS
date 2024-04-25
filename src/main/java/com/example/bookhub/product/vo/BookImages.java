package com.example.bookhub.product.vo;


import lombok.Data;

@Data
public class BookImages {
    private Long no;
    private String imageThumbnail;
    private String imageCover;
    private String imageDescription;
    private Book book;


}

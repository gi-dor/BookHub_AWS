package com.example.bookhub.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewForm {

    private long reviewNo;
    private long bookNo;
    private String createYn;
    private float rate;
    private long reviewTagNo;
    private String comment;
    private String buyerYn;
    private List<MultipartFile> imageList;
}

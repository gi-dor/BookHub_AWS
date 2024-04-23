package com.example.bookhub.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewForm {

    private long bookNo;
    private float rate;
    private long reviewTagNo;
    private String comment;
    private List<MultipartFile> imageList;
}

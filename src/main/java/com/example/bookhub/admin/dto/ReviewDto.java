package com.example.bookhub.admin.dto;

import com.example.bookhub.product.vo.Book;
import com.example.bookhub.user.vo.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {

    private String reviewComment;
    private User userName;
    private LocalDateTime reviewUpdateDate;
    private Book bookName;

}

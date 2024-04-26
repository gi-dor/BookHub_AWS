package com.example.bookhub.product.vo;


import java.time.LocalDateTime;
import lombok.Data;


@Data
public class Author {
    private long authorNo;
    private String name;
    private String introduction;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}

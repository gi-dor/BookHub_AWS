package com.example.bookhub.product.vo;


import lombok.Data;

@Data
import java.time.LocalDateTime;

public class Author {
    private long authorNo;
    private String name;
    private String introduction;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}

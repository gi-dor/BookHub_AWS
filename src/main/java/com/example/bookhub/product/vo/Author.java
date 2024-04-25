package com.example.bookhub.product.vo;


import lombok.Data;

import java.time.LocalDateTime;

@Data

public class Author {
    private long authorNo;
    private String name;
    private String introduction;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}

package com.example.bookhub.product.vo;

import lombok.Data;

@Data
public class Category {

    private long categoryNo;
    private String name;
    private long parentCategoryNo;
}

package com.example.bookhub.product.dto;

import lombok.Data;

@Data
public class CartBookDto {

    private Long no;
    private String title;
    private int priceSales;
    private int priceStandard;
    private String cover;
    private String categoryName;
    private int count;

}

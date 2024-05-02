package com.example.bookhub.admin.dto;

import com.example.bookhub.product.vo.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyDto {

    private Book bookName;
    private int soldCount;
    private int soldSum;
}

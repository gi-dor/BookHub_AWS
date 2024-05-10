package com.example.bookhub.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ReturnBook {

    private long returnNo;
    private long bookNo;
    private int count;
}

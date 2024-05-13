package com.example.bookhub.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReturnForm {

    private long buyNo;
    private long returnReasonNo;
    private List<Long> returnBookNoList;
    private List<Integer> returnCountList;
}

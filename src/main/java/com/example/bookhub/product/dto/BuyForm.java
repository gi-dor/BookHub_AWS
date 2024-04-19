package com.example.bookhub.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class BuyForm {
    private List<Long> buyBookNoList;
    private List<Integer> buyBookCountList;
    private List<Integer> selectedCouponList;
    private int totalPrice;
    private int deliveryPrice;
    private int totalBookDiscountPrice;
    private int totalCouponDiscountAmount;
    private int totalPointUseAmount;
    private int finalPrice;
    private int pointAccumulationAmount;
}

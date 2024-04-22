package com.example.bookhub.product.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CouponUsed {

    private long couponUsedNo;
    private int discountAmount;
    private long couponProducedNo;
    private long buyNo;
    private Date usedNo;
}

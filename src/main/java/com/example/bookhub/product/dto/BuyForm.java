package com.example.bookhub.product.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BuyForm {
    private List<Long> buyBookNoList;
    private List<Integer> buyBookCountList;
    private List<Long> couponProducedNoList;
    private List<Integer> couponDiscountAmountList;
    private int totalPrice;
    private int deliveryPrice;
    private int totalBookDiscountPrice;
    private int totalCouponDiscountAmount;
    private int totalPointUseAmount;
    private int finalPrice;
    private int pointAccumulationAmount;

    private long userDeliveryNo;
    private long buyDeliveryRequestNo;
    private String commonEntranceApproach;
    private long buyPayMethodNo;

    private String giftYn;
    private int receiverCount;
    private String senderName;
    private String sendMethod;
    private List<String> receiverName;
    private List<String> receiverEmail;
}

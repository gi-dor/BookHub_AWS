package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Buy {

    private Long buyNo;
    private int totalPrice;
    private int totalBookDiscountPrice;
    private int totalCouponDiscountAmount;
    private int totalPointUseAmount;
    private int finalPrice;
    private int pointAccumulationAmount;
    private String commonEntranceApproach;
    private String commonEntrancePassword;
    private Date buyDate;
    private String gift;
    private BuyPayMethod buyPayMethod;
    private BuyStatus buyStatus;
    private BuyDeliveryRequest buyDeliveryRequest;
    private User user;

    @Builder
    public Buy(Long buyNo, int totalPrice, int totalBookDiscountPrice, int totalCouponDiscountAmount, int totalPointUseAmount, int finalPrice, int pointAccumulationAmount,
               String commonEntranceApproach, String commonEntrancePassword, Date buyDate, String gift,
               BuyPayMethod buyPayMethod, BuyStatus buyStatus, BuyDeliveryRequest buyDeliveryRequest, User user) {
        super();
        this.buyNo = buyNo;
        this.totalPrice = totalPrice;
        this.totalBookDiscountPrice = totalBookDiscountPrice;
        this.totalCouponDiscountAmount = totalCouponDiscountAmount;
        this.totalPointUseAmount = totalPointUseAmount;
        this.finalPrice = finalPrice;
        this.pointAccumulationAmount = pointAccumulationAmount;
        this.commonEntranceApproach = commonEntranceApproach;
        this.commonEntrancePassword = commonEntrancePassword;
        this.buyDate = buyDate;
        this.gift = gift;
        this.buyPayMethod = buyPayMethod;
        this.buyStatus = buyStatus;
        this.buyDeliveryRequest = buyDeliveryRequest;
        this.user = user;
    }
}

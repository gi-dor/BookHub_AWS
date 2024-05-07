package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import java.time.LocalDateTime;
import com.example.bookhub.user.vo.UserDelivery;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private UserDelivery userDelivery;
    private String commonEntranceApproach;
    private String commonEntrancePassword;
    private LocalDateTime buyDate;
    private String giftYn;
    private BuyPayMethod buyPayMethod;
    private BuyStatus buyStatus;
    private BuyDeliveryRequest buyDeliveryRequest;
    private User user;
    private String orderId;
    private BuyBook buyBook;
    private Book book;
    private int cnt;


    @Builder
    public Buy(Long buyNo, int totalPrice, int totalBookDiscountPrice, int totalCouponDiscountAmount, int totalPointUseAmount, int finalPrice, int pointAccumulationAmount,
               UserDelivery userDelivery, String commonEntranceApproach, String commonEntrancePassword, LocalDateTime buyDate, String giftYn,
               BuyPayMethod buyPayMethod, BuyStatus buyStatus, BuyDeliveryRequest buyDeliveryRequest, User user) {
        super();
        this.buyNo = buyNo;
        this.totalPrice = totalPrice;
        this.totalBookDiscountPrice = totalBookDiscountPrice;
        this.totalCouponDiscountAmount = totalCouponDiscountAmount;
        this.totalPointUseAmount = totalPointUseAmount;
        this.finalPrice = finalPrice;
        this.pointAccumulationAmount = pointAccumulationAmount;
        this.userDelivery = userDelivery;
        this.commonEntranceApproach = commonEntranceApproach;
        this.commonEntrancePassword = commonEntrancePassword;
        this.buyDate = buyDate;
        this.giftYn = giftYn;
        this.buyPayMethod = buyPayMethod;
        this.buyStatus = buyStatus;
        this.buyDeliveryRequest = buyDeliveryRequest;
        this.user = user;
    }
}

package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.*;
import com.example.bookhub.user.vo.UserDelivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyMapper {
    List<CouponProduced> getCouponsByUserNo(long userNo);
    int getPointByUserNo(long userNo);
    void createBuy(Buy buy);
    void createBuyBook(BuyBook buyBook);
    void createCouponUsed(CouponUsed couponUsed);
    void updateCouponProducedUsed(@Param("couponProducedNo") long couponProducedNo, @Param("couponDiscountAmount") int couponDiscountAmount, @Param("used") String used);
    void updatePointUsed(Map<String, Object> map);
    List<UserDelivery> getUserDeliveryByUserNo(Long no);
    List<BuyDeliveryRequest> getBuyDeliveryRequest();
    void updateDefaultUserDeliveryN(long selectedUserDeliveryNo);
    void updateDefaultUserDeliveryY(long selectedUserDeliveryNo);
    void createUserDelivery(UserDelivery userDelivery);
    void updateBookStock(@Param("bookNo") long bookNo, @Param("count") int count);
    String getBuyerYn(@Param("bookNo") long bookNo, @Param("userNo") long userNo);
    int getBookStock(long bookNo);
    int getCouponProducedLastAmount(long couponProducedNo);
    int getCouponCountByUserNo(long userNo);
    void updatePointAccumulated(@Param("userNo") long userNo, @Param("pointAccumulationAmount") int pointAccumulationAmount);
}

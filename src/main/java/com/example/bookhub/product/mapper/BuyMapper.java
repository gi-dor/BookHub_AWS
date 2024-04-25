package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuyMapper {
    List<CouponProduced> getCouponsByUserNo(long userNo);
    int getPointByUserNo(long userNo);
    void createBuy(Buy buy);
    void createBuyBook(BuyBook buyBook);
    void createCouponUsed(CouponUsed couponUsed);
    void updateCouponProducedUsed(long couponProducedNo);
    void updatePointUsed(Map<String, Object> map);
    Buy getBuyByBuyNo(long buyNo);
    void createRefund(Refund refund);
}

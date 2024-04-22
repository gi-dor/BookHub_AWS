package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.BuyBook;
import com.example.bookhub.product.vo.CouponProduced;
import com.example.bookhub.product.vo.CouponUsed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuyMapper {
    List<CouponProduced> getCouponsByUserNo(long userNo);
    int getPointByUserNo(long userNo);
    void createBuy(Buy buy);
    void createBuyBook(BuyBook buyBook);
    void createCouponUsed(CouponUsed couponUsed);
    void updateCouponProducedUsed(long couponProducedNo);
}

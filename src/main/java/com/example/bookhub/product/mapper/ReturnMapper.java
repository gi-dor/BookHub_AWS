package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReturnMapper {
    Buy getBuyByBuyNo(long buyNo);
    void createBuyCancel(Return refund);
    void updateRefundYn(long buyNo);

    List<CouponUsed> getCouponUsedByBuyNo(long buyNo);
    void deleteCouponUsedByBuyNo(long buyNo);
    void updateCouponProduced(@Param("couponProducedNo") long couponProducedNo, @Param("discountAmount") int discountAmount);
    void updatePointUsedByUserNo(@Param("userNo") long userNo, @Param("totalPointUseAmount") int totalPointUseAmount);
    void updateBuyCancelPartBuy(Map<String, Object> map);
    void buyCancelPartBuyBook(@Param("buyNo") long buyNo, @Param("bookNo") long bookNo, @Param("count") int count);
    void deleteBuyCancelBuyBook();
    List<ReturnReason> getReturnReasonList();
    void insertRefund(Return returnProduct);
    void insertReturnBook(ReturnBook returnBook);
    Return getRefundByReturnNo(long refundNo);
    void updateBuyStatus(@Param("buyNo") long buyNo, @Param("buyStatusNo") long buyStatusNo);
    int getBuyBookCount(long buyNo);
    List<ReturnBook> getRefundBook(long returnNo);

}

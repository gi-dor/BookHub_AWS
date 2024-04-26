package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.Refund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RefundMapper {
    Buy getBuyByBuyNo(long buyNo);
    void createRefund(Refund refund);
    void updateRefundYn(long buyNo);

    List<Long> getCouponProducedNosByBuyNo(long buyNo);
    void deleteCouponUsedByBuyNo(long buyNo);
    void updateCouponProducedUsedByBuyNo(long buyNo);
    void updatePointUsedByUserNo(@Param("userNo") long userNo, @Param("totalPointUseAmount") int totalPointUseAmount);

}

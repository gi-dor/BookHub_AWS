package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.Refund;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefundMapper {
    Buy getBuyByBuyNo(long buyNo);
    void createRefund(Refund refund);
    void updateRefundYn(long buyNo);

}

package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Gift;
import com.example.bookhub.product.vo.GiftReceiver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GiftMapper {
    void insertGift(Gift gift);
    void insertGiftReceiver(GiftReceiver giftReceiver);
    void updateGiftReceiver(@Param("giftReceiverNo") long giftReceiverNo, @Param("userDeliveryNo") long userDeliveryNo, @Param("userNo") long userNo);
}

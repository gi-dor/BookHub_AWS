package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Gift;
import com.example.bookhub.product.vo.GiftReceiver;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GiftMapper {
    void insertGift(Gift gift);
    void insertGiftReceiver(GiftReceiver giftReceiver);
}

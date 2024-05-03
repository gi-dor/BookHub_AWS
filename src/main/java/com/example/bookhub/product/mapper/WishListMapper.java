package com.example.bookhub.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface WishListMapper {
    void createWishList(Map<String, Object> map);
    void deleteWishList(Map<String, Object> map);
    Optional<Long> getWishListYn(@Param("bookNo") long bookNo, @Param("userNo") long userNo);
}

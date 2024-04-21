package com.example.bookhub.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WishListMapper {
    void createWishList(Map<String, Object> map);
    void deleteWishList(Map<String, Object> map);
}

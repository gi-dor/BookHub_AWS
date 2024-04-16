package com.example.bookhub.product.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyMapper {
    long getBookNoByCartNo(long cartNo);
}

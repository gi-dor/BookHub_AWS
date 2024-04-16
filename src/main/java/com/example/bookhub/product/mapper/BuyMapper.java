package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.CouponProduced;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuyMapper {
    long getBookNoByCartNo(long cartNo);
    List<CouponProduced> getCouponsByUserNo(long userNo);
}

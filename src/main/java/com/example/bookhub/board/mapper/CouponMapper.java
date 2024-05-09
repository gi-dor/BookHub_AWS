package com.example.bookhub.board.mapper;

import com.example.bookhub.product.vo.Coupon;
import com.example.bookhub.product.vo.CouponProduced;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponMapper {

    void insertAttendanceCoupon(CouponProduced couponProduced);

    Coupon getCoupon(long couponNo);

}

package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.util.Date;

@Data
public class CouponProduced {

    private long couponProducedNo;
    private Date dueDate;
    private String used;
    private User user;
    private Coupon coupon;
}

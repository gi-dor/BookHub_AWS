package com.example.bookhub.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    int countCoupon(String id);

}

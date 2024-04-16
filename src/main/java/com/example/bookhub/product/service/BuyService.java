package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.vo.CouponProduced;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final UserMapper userMapper;
    private final BuyMapper buyMapper;

    public long getBookNoByCartNo(long cartNo){
        return buyMapper.getBookNoByCartNo(cartNo);
    }

    public List<CouponProduced> getCouponsByUserNo(String userId) {
        User user = userMapper.selectUserById(userId);
        return buyMapper.getCouponsByUserNo(user.getNo());
    }
}

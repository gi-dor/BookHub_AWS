package com.example.bookhub.user.service;

import com.example.bookhub.user.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageMapper myPageMapper;


    public int countCoupon (String id) {
       int cnt =  myPageMapper.countCoupon(id);
        System.out.println("보유한 쿠폰 갯수 : " + cnt );
       return cnt;
    }
}

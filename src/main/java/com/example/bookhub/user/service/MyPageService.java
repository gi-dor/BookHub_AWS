package com.example.bookhub.user.service;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.BuyBook;
import com.example.bookhub.user.mapper.MyPageMapper;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageMapper myPageMapper;
    private final UserMapper userMapper;

    public int countCoupon (String id) {
       int cnt =  myPageMapper.countCoupon(id);
        System.out.println("보유한 쿠폰 갯수 : " + cnt );
       return cnt;
    }

    public List<Buy> getOrderListById(String id) {
        User user = userMapper.selectUserById(id);
      return   myPageMapper.selectOrderListById(user.getId());
    }

    public List<Inquiry> getInquiryListById(String id) {
        User user = userMapper.selectUserById(id);
        return myPageMapper.selectInquiryList(user.getId());
    }


}

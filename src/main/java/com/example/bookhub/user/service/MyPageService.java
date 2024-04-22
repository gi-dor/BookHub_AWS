package com.example.bookhub.user.service;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.BuyBook;
import com.example.bookhub.user.dto.UserDetailsImpl;
import com.example.bookhub.user.mapper.MyPageMapper;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final PasswordEncoder passwordEncoder;
    private final MyPageMapper myPageMapper;
    private final UserMapper userMapper;
    private final UserService userService;

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


    public void deleteUserById(String id, String password) {
        // 사용자 아이디로 정보 가져오기
        User user =  userService.selectUserById(id);

        // 사용자가 입력한 비밀번호를 암호화하여 검증
        if (passwordEncoder.matches(password, user.getPassword())) {
            // deletedYn 'N' -> 'Y'
            myPageMapper.deleteUserById(user.getId());
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}

package com.example.bookhub.user.service;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.user.dto.ChangePasswordForm;
import com.example.bookhub.user.dto.PageWishListDTO;
import com.example.bookhub.user.dto.WishListDTO;
import com.example.bookhub.user.mapper.MyPageMapper;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserPagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
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

    public void updatePassword(String id, ChangePasswordForm form) {

        // 사용자 ID를 사용하여 데이터베이스에서 해당 사용자 정보를 가져오기
        User user = userMapper.selectUserById(id);

        // DB에서 가져온 사용자 비밀번호 가져오기
        String originalPassword = user.getPassword();

        // 사용자가 입력한 비밀번호 form.getPassword()
        // DB에서 가져온 비밀번호 originalPassword
        if (!passwordEncoder.matches(form.getPassword(), originalPassword)) {
            log.error("서비스 - 비밀번호가 일치하지 않은경우 id 확인 ",id);
            throw new RuntimeException("비밀번호 불일치");
        }

      // form.setChangePassword(passwordEncoder.encode(form.getChangePassword()));

        myPageMapper.updatePassword(id, passwordEncoder.encode(form.getChangePassword()));
    }


    public PageWishListDTO getWishListById(String id , int page) {
        User user = userMapper.selectUserById(id);

        // 사용자의 전체 찜 목록 개수를 조회
        int totalRows = myPageMapper.getTotalWishListCount(user.getId());

        // 사용자의 페이징 정보를 생성합니다.
        UserPagination userPagination = new UserPagination(page,totalRows);

        // offset에 getBegin사용
        int offset = userPagination.getBegin();

        // 사용자의 페이징 정보를 기반으로 찜 목록을 가져온다
        List<WishListDTO> wishListDTO =   myPageMapper.selectWishListById(user.getId() , offset);

        return  new PageWishListDTO(wishListDTO,userPagination );
    }

    public int getTotalWishListCount(String id) {
        return myPageMapper.getTotalWishListCount(id);
    }



}

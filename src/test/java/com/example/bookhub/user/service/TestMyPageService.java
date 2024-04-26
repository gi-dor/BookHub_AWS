package com.example.bookhub.user.service;

import com.example.bookhub.product.service.WishListService;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMyPageService {

    @Autowired
    WishListService wishListService;

//    private final long BOOK_NO = 9791196222747L; // 책 번호
    private final String USER_ID = "rltjs987"; // 사용자 아이디

    @RepeatedTest(100) // 10000번 반복하는 테스트
    public void testCreateWishList(RepetitionInfo repetitionInfo) {
        long bookNo = 9791196222747L + repetitionInfo.getCurrentRepetition() - 1; // 1씩 증가하도록 설정
        wishListService.createWishList(bookNo, USER_ID);
        // 추가적인 검증 코드를 작성할 수 있음
    }

}

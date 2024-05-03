package com.example.bookhub.user.service;

import com.example.bookhub.board.service.InquiryService;
import com.example.bookhub.product.service.WishListService;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMyPageService {

    @Autowired
    WishListService wishListService;

    @Autowired
    InquiryService inquiryService;



//    private final long BOOK_NO = 9791196222747L; // 책 번호
    private final String USER_ID = "rltjs987"; // 사용자 아이디

    @RepeatedTest(1) // 10000번 반복하는 테스트
    public void testCreateWishList(RepetitionInfo repetitionInfo) {
        long bookNo = 9791196222743L + repetitionInfo.getCurrentRepetition() - 1; // 1씩 증가하도록 설정
        wishListService.createWishList(bookNo, USER_ID);
        // 추가적인 검증 코드를 작성할 수 있음
    }

    @RepeatedTest(100000)
    public void testInsertInquiry(RepetitionInfo repetitionInfo) {

        // 테스트 데이터
        int currentRepetition = repetitionInfo.getCurrentRepetition();

        String title = "더미테스트 제목" + currentRepetition;
        String content = "더미테스트 내용부분";
        String userId = "rltjs987"; // 사용자 ID
        long catNo = 3L; // 카테고리 번호

        int totalRepetitions = repetitionInfo.getTotalRepetitions();

        inquiryService.insertInquiry(title,content,userId,catNo);
        System.out.println("###########   현재 반복: " + currentRepetition + " / 총 반복: " + totalRepetitions +" ############");
    }

}

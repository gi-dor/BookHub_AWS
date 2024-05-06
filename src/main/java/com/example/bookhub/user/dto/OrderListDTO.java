package com.example.bookhub.user.dto;

import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.BuyPayMethod;
import com.example.bookhub.product.vo.BuyStatus;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserPagination;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {

    // 주문번호
    Long no;

    // 상품 구매가격
    Long finalPrice;

    // 주문상품 이름
    Book book;

    // 구매 날짜
    LocalDateTime buyDate;

    // 결제방법
    BuyPayMethod buyPayMethod;

    // 구매 상태
    BuyStatus buyStatus;

    // 구매자 정보
    User user;

    //  여러개 구매시 xxxxx 외 cnt 개  표현하려고
    int cnt;

}

package com.example.bookhub.user.dto;

import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.BuyStatus;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserDelivery;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    // 주문번호 buyNo
    Long no;

    // 주문상품 bookName
    Book book;

    // 주문상태 statusName
    BuyStatus buyStatus;

    // 주문일자
    LocalDateTime buyDate;

    // 책원가  book.price
    // 책 할인율 book.discountRate

    // 할인금액 : 원가 - (원가*할인율) 소수점 버림
    Long discountedPrice;

    // 할인적용된 금액
    Long resultPrice;

    // 주문금액
    Long finalPrice;


    // 배송 상세내역
    // 주문한 사람
    User user;

    // 받는 사람 - 이름 , 주소 , 번호
    UserDelivery userDelivery;

    public String getFullAddress(){
            return userDelivery.getFullAddress();
    }




}

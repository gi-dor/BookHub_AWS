package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.BuyBook;
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

    public List<CouponProduced> getCouponsByUserNo(String userId) {
        User user = userMapper.selectUserById(userId);
        return buyMapper.getCouponsByUserNo(user.getNo());
    }

    public int getPointByUserNo(String userId) {
        User user = userMapper.selectUserById(userId);
        return buyMapper.getPointByUserNo(user.getNo());
    }

    public void createBuy(BuyForm buyForm, String userId) {
        // BUY 테이블
        Buy buy = Buy.builder()
                .totalPrice(buyForm.getTotalPrice())
                .totalBookDiscountPrice(buyForm.getTotalBookDiscountPrice())
                .totalCouponDiscountAmount(buyForm.getTotalCouponDiscountAmount())
                .totalPointUseAmount(buyForm.getTotalPointUseAmount())
                .finalPrice(buyForm.getFinalPrice())
                .build();

        User user = userMapper.selectUserById(userId);
        buy.setUser(user);

        buyMapper.createBuy(buy);
        Long generatedNo = buy.getBuyNo();

        // BUYBOOK 테이블
        for(int i = 0; i < buyForm.getBuyBookNoList().size(); i++){
            long bookNo = buyForm.getBuyBookNoList().get(i);
            int count = buyForm.getBuyBookCountList().get(i);

            BuyBook buyBook = BuyBook.builder()
                    .bookNo(bookNo)
                    .count(count)
                    .build();

            buyBook.setBuyNo(generatedNo);

            buyMapper.createBuyBook(buyBook);
        }
    }
}

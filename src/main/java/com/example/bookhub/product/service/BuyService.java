package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.vo.*;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional
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

        // COUPON_USED 테이블, COUPON_PRODUCED 테이블
        if(buyForm.getCouponProducedNoList() != null) {
            for (int i = 0; i < buyForm.getCouponProducedNoList().size(); i++) {
                long couponProducedNo = buyForm.getCouponProducedNoList().get(i);
                int couponDiscountAmount = buyForm.getCouponDiscountAmountList().get(i);

                CouponUsed couponUsed = new CouponUsed();
                couponUsed.setBuyNo(generatedNo);
                couponUsed.setCouponProducedNo(couponProducedNo);
                couponUsed.setDiscountAmount(couponDiscountAmount);

                buyMapper.createCouponUsed(couponUsed);
                buyMapper.updateCouponProducedUsed(couponProducedNo);
            }
        }

        // 포인트 차감
        int totalPointUseAmount = buyForm.getTotalPointUseAmount();
        Map<String, Object> map = new HashMap<>();
        map.put("userNo", user.getNo());
        map.put("totalPointUseAmount", totalPointUseAmount);
        buyMapper.updatePointUsed(map);
    }
}

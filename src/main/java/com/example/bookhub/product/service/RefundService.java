package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.RefundMapper;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.Refund;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundMapper refundMapper;
    private final UserMapper userMapper;

    public Buy getBuyByBuyNo(long buyNo) {
        return refundMapper.getBuyByBuyNo(buyNo);
    }

    @Transactional
    public void refund(Buy buy, String userId){
        User user = userMapper.selectUserById(userId);

        createRefund(buy.getBuyNo(), user);
        updateRefundYn(buy.getBuyNo());
        revertCouponAndPoint(buy, user.getNo());
    }

    public void createRefund(long buyNo, User user) {

        Refund refund = new Refund();

        Buy buy = new Buy();
        buy.setBuyNo(buyNo);
        refund.setBuy(buy);

        refund.setUser(user);

        refundMapper.createRefund(refund);
    }

    public void updateRefundYn(long buyNo) {

        refundMapper.updateRefundYn(buyNo);
    }

    public void revertCouponAndPoint(Buy buy, long userNo){
        List<Long> couponProducedNoList = refundMapper.getCouponProducedNosByBuyNo(buy.getBuyNo());
        refundMapper.deleteCouponUsedByBuyNo(buy.getBuyNo());
        for(long couponProducedNo : couponProducedNoList){
            refundMapper.updateCouponProducedUsedByBuyNo(couponProducedNo);
        }
        refundMapper.updatePointUsedByUserNo(userNo, buy.getTotalPointUseAmount());
    }
}

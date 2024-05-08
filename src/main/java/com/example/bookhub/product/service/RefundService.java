package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.ReturnForm;
import com.example.bookhub.product.mapper.BookMapper;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.mapper.RefundMapper;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.Refund;
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
public class RefundService {

    private final RefundMapper refundMapper;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final BuyMapper buyMapper;
    private ReturnForm returnForm;

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

    public Map<String, Object> calculateReturnPrice(ReturnForm returnForm) {
        int totalReturnPrice = 0;
        int finalReturnPrice = 0;
        int totalBookDiscountReturnPrice = 0;

        this.returnForm = returnForm;
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++){
            BookDto book = bookMapper.getBookDetailByNo(returnForm.getReturnBookNoList().get(i));
            System.out.println("book: " + book);
            totalReturnPrice += book.getPrice();
            finalReturnPrice += book.getPrice() * (1 - book.getDiscountRate());
            totalBookDiscountReturnPrice = totalReturnPrice - finalReturnPrice;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("buyNo", returnForm.getBuyNo());
        map.put("totalReturnPrice", totalReturnPrice);
        map.put("finalReturnPrice", finalReturnPrice);
        map.put("totalBookDiscountReturnPrice", totalBookDiscountReturnPrice);

        return map;
    }

    public void refundPart(Map<String, Object> map) {
        System.out.println(map);
        refundMapper.refundPartBuy(map);
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++) {
            long bookNo = returnForm.getReturnBookNoList().get(i);
            int count = returnForm.getReturnCountList().get(i);
            refundMapper.refundPartBuyBook(returnForm.getBuyNo(), bookNo, count);
        }
        refundMapper.deleteRefundBuyBook();
    }
}

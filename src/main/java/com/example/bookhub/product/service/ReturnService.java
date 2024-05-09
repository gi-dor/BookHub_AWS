package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.KakaoCancelResponse;
import com.example.bookhub.product.dto.ReturnForm;
import com.example.bookhub.product.mapper.BookMapper;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.mapper.ReturnMapper;
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
public class ReturnService {

    private final ReturnMapper returnMapper;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final BuyMapper buyMapper;
    private final KakaoPayService kakaoPayService;
    private ReturnForm returnForm;

    public Buy getBuyByBuyNo(long buyNo) {
        return returnMapper.getBuyByBuyNo(buyNo);
    }

    @Transactional
    public void buyCancel(Buy buy, String userId){
        User user = userMapper.selectUserById(userId);

        //createBuyCancel(buy.getBuyNo(), user);
        updateRefundYn(buy.getBuyNo());
        revertCouponAndPoint(buy, user);
    }

//    public void createBuyCancel(long buyNo, User user) {
//
//        Refund refund = new Refund();
//
//        Buy buy = new Buy();
//        buy.setBuyNo(buyNo);
//        refund.setBuy(buy);
//
//        refund.setUser(user);
//
//        refundMapper.createRefund(refund);
//    }

    public void updateRefundYn(long buyNo) {

        returnMapper.updateRefundYn(buyNo);
    }

    public void buyCancelPart(Map<String, Object> map) {
        System.out.println(map);
        returnMapper.updateBuyCancelPartBuy(map);
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++) {
            long bookNo = returnForm.getReturnBookNoList().get(i);
            int count = returnForm.getReturnCountList().get(i);
            returnMapper.buyCancelPartBuyBook(returnForm.getBuyNo(), bookNo, count);
        }
        returnMapper.deleteBuyCancelBuyBook();
    }

    public List<ReturnReason> getReturnReasonList() {
        return returnMapper.getReturnReasonList();
    }

    @Transactional
    public void createRefund(Buy buy, ReturnForm returnForm, String userId) {
        User user = userMapper.selectUserById(userId);

        long returnNo = insertRefund(buy, returnForm, user);
        insertRefundBook(returnNo, returnForm);
        returnMapper.updateBuyStatus(buy.getBuyNo(), 7);
    }

    public long insertRefund(Buy buy, ReturnForm returnForm, User user){
        Map<String, Object> map = calculateReturnPrice(returnForm);

        ReturnReason returnReason = new ReturnReason();
        returnReason.setReturnReasonNo(returnForm.getReturnReasonNo());

        Return returnProduct = Return.builder()
                .returnReason(returnReason)
                .buy(buy)
                .user(user)
                .price((Integer)map.get("finalReturnPrice"))
                .type("환불")
                .build();

        returnMapper.insertRefund(returnProduct);
        return returnProduct.getReturnNo();
    }

    public Map<String, Object> calculateReturnPrice(ReturnForm returnForm) {
        System.out.println(returnForm);
        int totalReturnPrice = 0;
        int finalReturnPrice = 0;
        int totalBookDiscountReturnPrice = 0;

        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++){
            BookDto book = bookMapper.getBookDetailByNo(returnForm.getReturnBookNoList().get(i));
            totalReturnPrice += book.getPrice() * returnForm.getReturnCountList().get(i);
            finalReturnPrice += book.getPrice() * (1 - book.getDiscountRate()) * returnForm.getReturnCountList().get(i);
            totalBookDiscountReturnPrice = (int)(book.getPrice() * book.getDiscountRate()) * returnForm.getReturnCountList().get(i);
        }

        Buy buy =  buyMapper.getTotalCouponDiscountAmount(returnForm.getBuyNo());
        finalReturnPrice = finalReturnPrice - buy.getTotalCouponDiscountAmount() - buy.getTotalPointUseAmount();

        Map<String, Object> map = new HashMap<>();
        map.put("buyNo", returnForm.getBuyNo());
        map.put("totalReturnPrice", totalReturnPrice);
        map.put("finalReturnPrice", finalReturnPrice);
        map.put("totalBookDiscountReturnPrice", totalBookDiscountReturnPrice);

        return map;
    }

    public void insertRefundBook(long returnNo, ReturnForm returnForm){
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++){
            long bookNo = returnForm.getReturnBookNoList().get(i);
            int count = returnForm.getReturnCountList().get(i);

            ReturnBook returnBook = ReturnBook.builder()
                            .returnNo(returnNo)
                            .bookNo(bookNo)
                            .count(count)
                            .build();
            returnMapper.insertReturnBook(returnBook);
        }
    }

    @Transactional
    public void refundApprove(long returnNo){
        Return returnProduct = returnMapper.getRefundByReturnNo(returnNo);
        List<ReturnBook> returnBookList = returnMapper.getRefundBook(returnNo);

        returnMapper.updateBuyStatus(returnProduct.getBuy().getBuyNo(), 8);

        // 전체환불일 경우 쿠폰, 포인트 되돌리기
        revertCouponAndPointYn(returnProduct, returnBookList);

        //재고 되돌리기
        for(ReturnBook returnBook : returnBookList) {
            bookMapper.returnBookStock(returnBook.getBookNo(), returnBook.getCount());
        }

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(returnProduct.getBuy().getOrderId(), returnProduct.getPrice());
    }

    public void revertCouponAndPointYn(Return returnProduct, List<ReturnBook> returnBookList){
        int buyBookTotalCount = returnMapper.getBuyBookCount(returnProduct.getBuy().getBuyNo());
        int returnTotalCount = 0;
        for(ReturnBook returnBook : returnBookList){
            returnTotalCount += returnBook.getCount();
        }
        if(buyBookTotalCount == returnTotalCount)
            revertCouponAndPoint(returnProduct.getBuy(), returnProduct.getUser());
    }

    public void revertCouponAndPoint(Buy buy, User user){
        List<CouponUsed> couponUsedList = returnMapper.getCouponUsedByBuyNo(buy.getBuyNo());
        // returnMapper.deleteCouponUsedByBuyNo(buy.getBuyNo());
        for(CouponUsed couponUsed : couponUsedList){
            returnMapper.updateCouponProduced(couponUsed.getCouponProducedNo(), couponUsed.getDiscountAmount());
        }
        returnMapper.updatePointUsedByUserNo(user.getNo(), buy.getTotalPointUseAmount());
    }
}

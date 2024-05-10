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

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReturnService {

    private final ReturnMapper returnMapper;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final KakaoPayService kakaoPayService;
    private Set<String> processedBuyNoSet = new HashSet<>();


    @Transactional
    public int createBuyCancel(ReturnForm returnForm, String userId) {

        Map<String, Object> map = calculateReturnPrice(returnForm);
        Buy buy = returnMapper.getBuyByBuyNo(returnForm.getBuyNo());
        User user = userMapper.selectUserById(userId);

        if(BuyCancelAllYn(returnForm)) {
            revertCouponAndPoint(buy, user);
            returnMapper.deleteCouponUsedByBuyNo(returnForm.getBuyNo());
            returnMapper.deleteBuyCancelAllBuyBook(returnForm.getBuyNo());
            returnMapper.deleteBuyCancelBuy(returnForm.getBuyNo());
        }
        else{
            updateBuyAndBuyBook(returnForm, map);
            returnMapper.deleteBuyCancelBuyBook(returnForm.getBuyNo());
        }

        updateBookStock(returnForm);
        returnMapper.updateBuyStatus(buy.getBuyNo(), 6);

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(buy.getOrderId(), (Integer) map.get("finalReturnPrice"));
        return (Integer) map.get("finalReturnPrice");
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

        if (!processedBuyNoSet.contains(returnForm.getBuyNo())) {
            Buy buy = returnMapper.getTotalDiscountAmount(returnForm.getBuyNo());
            finalReturnPrice = finalReturnPrice - buy.getTotalCouponDiscountAmount() - buy.getTotalPointUseAmount();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("buyNo", returnForm.getBuyNo());
        map.put("totalReturnPrice", totalReturnPrice);
        map.put("finalReturnPrice", finalReturnPrice);
        map.put("totalBookDiscountReturnPrice", totalBookDiscountReturnPrice);

        return map;
    }

    public boolean BuyCancelAllYn(ReturnForm returnForm){
        int buyBookTotalCount = returnMapper.getBuyBookCount(returnForm.getBuyNo());
        int buyCancelTotalCount = 0;
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++)
            buyCancelTotalCount += returnForm.getReturnCountList().get(i);

        if(buyBookTotalCount == buyCancelTotalCount)
            return true;
        else
            return false;
    }

    public void updateBuyAndBuyBook(ReturnForm returnForm, Map<String, Object> map) {
        returnMapper.updateBuyCancelBuy(map);
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++) {
            long bookNo = returnForm.getReturnBookNoList().get(i);
            int count = returnForm.getReturnCountList().get(i);
            returnMapper.buyCancelBuyBook(returnForm.getBuyNo(), bookNo, count);
        }
    }

    private void updateBookStock(ReturnForm returnForm) {
        for(int i = 0; i < returnForm.getReturnBookNoList().size(); i++) {
            long bookNo = returnForm.getReturnBookNoList().get(i);
            int count = returnForm.getReturnCountList().get(i);
            bookMapper.returnBookStock(bookNo, count);
        }
    }



    public List<ReturnReason> getReturnReasonList() {
        return returnMapper.getReturnReasonList();
    }

    @Transactional
    public void createRefund(ReturnForm returnForm, String userId) {
        Buy buy = returnMapper.getBuyByBuyNo(returnForm.getBuyNo());
        User user = userMapper.selectUserById(userId);

        long returnNo = insertRefund(buy, returnForm, user);
        insertReturnBook(returnNo, returnForm);
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

        returnMapper.insertReturn(returnProduct);
        return returnProduct.getReturnNo();
    }

    public void insertReturnBook(long returnNo, ReturnForm returnForm){
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
    public int refundApprove(long returnNo){
        Return returnProduct = returnMapper.getRefundByReturnNo(returnNo);
        List<ReturnBook> returnBookList = returnMapper.getRefundBook(returnNo);

        returnMapper.updateBuyStatus(returnProduct.getBuy().getBuyNo(), 8);

        // 전체환불일 경우 쿠폰, 포인트 되돌리기
        refundAllYn(returnProduct, returnBookList);

        //재고 되돌리기
        for(ReturnBook returnBook : returnBookList) {
            bookMapper.returnBookStock(returnBook.getBookNo(), returnBook.getCount());
        }

        returnMapper.updateReturnedYn(returnNo);
        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(returnProduct.getBuy().getOrderId(), returnProduct.getPrice());
        return kakaoCancelResponse.getApproved_cancel_amount().getTotal();
    }

    public void refundAllYn(Return returnProduct, List<ReturnBook> returnBookList){
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

    public void createExchange(ReturnForm returnForm, String userId) {
        Buy buy = returnMapper.getBuyByBuyNo(returnForm.getBuyNo());
        User user = userMapper.selectUserById(userId);

        long returnNo = insertExchange(buy, returnForm, user);
        insertReturnBook(returnNo, returnForm);
        returnMapper.updateBuyStatus(buy.getBuyNo(), 9);
    }

    public long insertExchange(Buy buy, ReturnForm returnForm, User user){
        ReturnReason returnReason = new ReturnReason();
        returnReason.setReturnReasonNo(returnForm.getReturnReasonNo());

        Return returnProduct = Return.builder()
                .returnReason(returnReason)
                .buy(buy)
                .user(user)
                .type("교환")
                .build();

        returnMapper.insertReturn(returnProduct);
        return returnProduct.getReturnNo();
    }
}

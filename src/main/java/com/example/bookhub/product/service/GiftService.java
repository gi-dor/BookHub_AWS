package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.exception.BookHubException;
import com.example.bookhub.product.mapper.BookMapper;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.mapper.GiftMapper;
import com.example.bookhub.product.vo.*;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    private final BuyMapper buyMapper;
    private final GiftMapper giftMapper;
    private final JavaMailSender emailSender;

    private final BuyService buyService;
    private BuyForm buyForm;

    @Value("${spring.mail.from}")
    private String setFrom;


    public void setGiftYn(BuyForm buyForm){
        buyForm.setGiftYn("Y");
    }

    @Transactional
    public void createGift(BuyForm buyForm, String tid, String userId) {
        this.buyForm = buyForm;
        User user = userMapper.selectUserById(userId);

        // 재고 감소
        updateBookStock(buyForm);

        // BUY 테이블
        Buy buy = insertBuy(tid, user);

        // BUYBOOK 테이블
        insertBuyBook(buy.getBuyNo());

        Gift gift = insertGift(buy);
        System.out.println(gift);

        List<GiftReceiver> giftReceiverList = insertGiftReceiver(gift);
        System.out.println(giftReceiverList);

        // COUPON_USED 테이블, COUPON_PRODUCED 테이블
        insertAndUpdateCoupon(buy.getBuyNo());

        // 포인트 차감
        updatePoint(user);

        for(int i = 0; i < giftReceiverList.size(); i++){
            long giftReceiverNo = giftReceiverList.get(i).getGiftReceiverNo();
            String receiverName = giftReceiverList.get(i).getName();
            String receiverEmail = giftReceiverList.get(i).getEmail();
            makeEmail(giftReceiverNo, receiverEmail, receiverName);
        }
    }

    public Buy insertBuy(String tid, User user){

        BuyPayMethod buyPayMethod = new BuyPayMethod();
        buyPayMethod.setBuyPayMethodNo(buyForm.getBuyPayMethodNo());

        Buy buy = Buy.builder()
                .totalPrice(buyForm.getTotalPrice())
                .totalBookDiscountPrice(buyForm.getTotalBookDiscountPrice())
                .totalCouponDiscountAmount(buyForm.getTotalCouponDiscountAmount())
                .totalPointUseAmount(buyForm.getTotalPointUseAmount())
                .finalPrice(buyForm.getFinalPrice())
                .commonEntranceApproach(buyForm.getCommonEntranceApproach())
                .buyPayMethod(buyPayMethod)
                .giftYn(buyForm.getGiftYn())
                .build();

        buy.setOrderId(tid);

        buy.setUser(user);

        buyMapper.createBuy(buy);
        return buy;
    }

    public void insertBuyBook(long generatedNo){
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

    public Gift insertGift(Buy buy){

        Gift gift = Gift.builder()
                .senderName(buyForm.getSenderName())
                .sendMethod(buyForm.getSendMethod())
                .buy(buy)
                .build();

        giftMapper.insertGift(gift);
        return gift;
    }

    public List<GiftReceiver> insertGiftReceiver(Gift gift){
        List<GiftReceiver> giftReceiverList = new ArrayList<>();

        for(int i = 0; i < buyForm.getReceiverName().size(); i++) {
            String receiverName = buyForm.getReceiverName().get(i);
            String receiverEmail = buyForm.getReceiverEmail().get(i);
            GiftReceiver giftReceiver = GiftReceiver.builder()
                    .name(receiverName)
                    .email(receiverEmail)
                    .gift(gift)
                    .build();
            giftMapper.insertGiftReceiver(giftReceiver);
            giftReceiverList.add(giftReceiver);
        }
        return giftReceiverList;
    }

    public void insertAndUpdateCoupon(long generatedNo){
        if(buyForm.getCouponProducedNoList() != null) {
            for (int i = 0; i < buyForm.getCouponProducedNoList().size(); i++) {
                long couponProducedNo = buyForm.getCouponProducedNoList().get(i);
                int couponDiscountAmount = buyForm.getCouponDiscountAmountList().get(i);

                CouponUsed couponUsed = new CouponUsed();
                couponUsed.setBuyNo(generatedNo);
                couponUsed.setCouponProducedNo(couponProducedNo);
                couponUsed.setDiscountAmount(couponDiscountAmount);

                buyMapper.createCouponUsed(couponUsed);

                int lastAmount = buyMapper.getCouponProducedLastAmount(couponProducedNo);
                if(lastAmount > couponDiscountAmount)
                    buyMapper.updateCouponProducedUsed(couponProducedNo, couponDiscountAmount, "N");
                else
                    buyMapper.updateCouponProducedUsed(couponProducedNo, couponDiscountAmount, "Y");
            }
        }
    }

    public void updateBookStock(BuyForm buyForm){
        for(int i = 0; i < buyForm.getBuyBookNoList().size(); i++) {
            long bookNo = buyForm.getBuyBookNoList().get(i);
            int count = buyForm.getBuyBookCountList().get(i);

            BookDto book = bookMapper.getBookByBookNo(bookNo);
            if(book.getStock() > count){
                buyMapper.updateBookStock(bookNo, count);
            }
            else {
                throw new BookHubException("재고가 부족하여 주문이 취소되었습니다");
            }
        }
    }

    public void updatePoint(User user){
        int totalPointUseAmount = buyForm.getTotalPointUseAmount();
        Map<String, Object> map = new HashMap<>();
        map.put("userNo", user.getNo());
        map.put("totalPointUseAmount", totalPointUseAmount);
        buyMapper.updatePointUsed(map);
    }

    public void makeEmail(long giftReceiverNo, String receiverMail, String receiverName){
        String url = "http://localhost:8080/product/gift/receiver/" + giftReceiverNo;
        String title = "북허브 도서 선물이 도착했습니다";
        String content = "<html><body>" + receiverName + "님, 북허브 도서 선물이 도착했습니다. <br/>" +
                "주소를 입력하여 선물을 받아보세요!<br/><a href='" + url + "'>" + url + "</a></body></html>";
        sendMail(setFrom, receiverMail, title, content);
    }

    private void sendMail(String setFrom, String toMail, String title, String content) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 발송 오류", e);
        }
    }
}

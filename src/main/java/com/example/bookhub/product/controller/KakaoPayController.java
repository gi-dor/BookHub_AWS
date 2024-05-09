package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.dto.KakaoApproveResponse;
import com.example.bookhub.product.dto.KakaoCancelResponse;
import com.example.bookhub.product.dto.ReturnForm;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayBusinessLogicException;
import com.example.bookhub.product.service.BuyService;
import com.example.bookhub.product.service.GiftService;
import com.example.bookhub.product.service.KakaoPayService;
import com.example.bookhub.product.service.ReturnService;
import com.example.bookhub.product.vo.Buy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Map;


@Controller
@RequestMapping("/kakaoPay")
@RequiredArgsConstructor
@SessionAttributes({"buyForm"})
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final BuyService buyService;
    private final GiftService giftService;
    private final ReturnService returnService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public String kakaoPayReady(@ModelAttribute BuyForm buyForm) {
        kakaoPayService.setRestTemplate(new RestTemplate());
        return "redirect:" + kakaoPayService.kakaoPayReady(buyForm);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/success")
    public String kakaoPaySuccess(@ModelAttribute BuyForm buyForm, Principal principal,
                                  @RequestParam("pg_token")String pgToken, Model model) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        if(kakaoApprove != null) {
            if("Y".equals(buyForm.getGiftYn()))
                giftService.createGift(buyForm, kakaoApprove.getTid(), principal.getName());
            else
                buyService.createBuy(buyForm, kakaoApprove.getTid(), principal.getName());
        }

        model.addAttribute("successMessage", "결제 성공");
        model.addAttribute("finalPrice", buyForm.getFinalPrice());
        return "product/pay/success";
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public String cancel() {
        throw new KakaoPayBusinessLogicException("카카오 결제 진행 중 취소 실패");
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public String fail() {
        throw new KakaoPayBusinessLogicException("카카오 결제 실패");
    }

    @PostMapping("/buyCancel")
    public String buyCancel(long buyNo, Model model, Principal principal) {
        Buy buy = returnService.getBuyByBuyNo(buyNo);
        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(buy.getOrderId(), buy.getFinalPrice());
        if(kakaoCancelResponse != null) {
            returnService.buyCancel(buy, principal.getName());
        }

        int finalPrice = kakaoCancelResponse.getApproved_cancel_amount().getTotal();
        model.addAttribute("successMessage", "결제 취소 성공");
        model.addAttribute("finalPrice", finalPrice);

        return "product/pay/success";
    }

    @PostMapping("/buyCancel/part")
    public String buyCancelPart(Model model, ReturnForm returnForm) {
        Map<String, Object> map = returnService.calculateReturnPrice(returnForm);
        Buy buy = returnService.getBuyByBuyNo(returnForm.getBuyNo());
        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(buy.getOrderId(), (Integer) map.get("finalReturnPrice"));
        if(kakaoCancelResponse != null) {
            returnService.buyCancelPart(map);
        }

        int finalPrice = kakaoCancelResponse.getApproved_cancel_amount().getTotal();
        model.addAttribute("successMessage", "결제 취소 성공");
        model.addAttribute("finalPrice", finalPrice);

        return "product/pay/success";
    }
}

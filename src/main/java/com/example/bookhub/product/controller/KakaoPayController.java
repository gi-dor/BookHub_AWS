package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.dto.KakaoApproveResponse;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayBusinessLogicException;
import com.example.bookhub.product.service.BuyService;
import com.example.bookhub.product.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/kakaoPay")
@RequiredArgsConstructor
@SessionAttributes({"buyForm"})
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final BuyService buyService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public String kakaoPayReady(@ModelAttribute BuyForm buyForm) {
        return "redirect:" + kakaoPayService.kakaoPayReady(buyForm);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/success")
    public String kakaoPaySuccess(@ModelAttribute BuyForm buyForm, Principal principal,
                                  @RequestParam("pg_token")String pgToken, Model model) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        buyService.createBuy(buyForm, principal.getName());
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
}

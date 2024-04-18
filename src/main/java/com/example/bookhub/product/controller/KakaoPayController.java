package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.dto.KakaoApproveResponse;
import com.example.bookhub.product.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/kakaoPay")
@RequiredArgsConstructor
@SessionAttributes({"buyForm"})
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public String kakaoPayReady(@ModelAttribute BuyForm buyForm) {
        //System.out.println(buyForm);
        return "redirect:" + kakaoPayService.kakaoPayReady();
    }

    @GetMapping("/success")
    public String kakaoPaySuccess(@ModelAttribute BuyForm buyForm, @RequestParam("pg_token")String pgToken, Model model) {
        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        System.out.println(kakaoApprove.toString());
        System.out.println(kakaoApprove.getAmount().toString());
        System.out.println(buyForm);
        return "product/pay/success";
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public String cancel() {
        return "/product/pay/error";
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public String fail() {
        return "/product/pay/error";
    }
}

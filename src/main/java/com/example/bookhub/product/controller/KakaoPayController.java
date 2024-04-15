package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

@Controller
@RequestMapping("/kakaoPay")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public String kakaoPayReady() {

        return "redirect:" + kakaoPayService.kakaoPayReady();
    }

    @GetMapping("/success")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model) {
        //KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {
        throw new RuntimeException();
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {
        throw new RuntimeException();
    }
}

package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.KakaoCancelResponse;
import com.example.bookhub.product.dto.ReturnForm;
import com.example.bookhub.product.service.KakaoPayService;
import com.example.bookhub.product.service.ReturnService;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.Return;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/product/return")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @PostMapping("/refund")
    public String refundPart(ReturnForm returnForm, Principal principal){
        Buy buy = returnService.getBuyByBuyNo(returnForm.getBuyNo());
        returnService.createRefund(buy, returnForm, principal.getName());
        return "/product/return/success";
    }

    @GetMapping("/refund/approve/{returnNo}")
    public ResponseEntity<String> refundApprove(@PathVariable("returnNo") long refundNo){
         returnService.refundApprove(refundNo);

        return ResponseEntity.ok().body("환불승인완료");
    }
}

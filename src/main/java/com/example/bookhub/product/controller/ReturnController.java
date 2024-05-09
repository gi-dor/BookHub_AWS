package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.ReturnForm;
import com.example.bookhub.product.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/product/return")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @PostMapping("/buyCancel")
    public String buyCancel(Model model, ReturnForm returnForm, Principal principal) {
        int finalPrice = returnService.createBuyCancel(returnForm, principal.getName());

        model.addAttribute("successMessage", "결제 취소 성공");
        model.addAttribute("finalPrice", finalPrice);

        return "product/pay/success";
    }

    @PostMapping("/refund")
    public String refund(ReturnForm returnForm, Principal principal, Model model){
        returnService.createRefund(returnForm, principal.getName());

        model.addAttribute("returnType", "환불");
        return "/product/return/success";
    }

    @GetMapping("/refund/approve/{returnNo}")
    public ResponseEntity<String> refundApprove(@PathVariable("returnNo") long refundNo){
         returnService.refundApprove(refundNo);

        return ResponseEntity.ok().body("환불승인완료");
    }

    @PostMapping("/exchange")
    public String exchange(ReturnForm returnForm, Principal principal, Model model){
        returnService.createExchange(returnForm, principal.getName());

        model.addAttribute("returnType", "교환");
        return "/product/return/success";
    }


}

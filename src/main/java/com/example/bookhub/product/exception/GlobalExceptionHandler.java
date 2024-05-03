package com.example.bookhub.product.exception;

import com.example.bookhub.product.exception.kakaoPay.KakaoPayApproveException;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayBusinessLogicException;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayReadyException;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayRefundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(BookHubException.class)
    public String bookHubExceptionHandler(BookHubException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "product/pay/error";
    }

    @ExceptionHandler(KakaoPayReadyException.class)
    public String kakaoPayReadyExceptionHandler(KakaoPayReadyException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "product/pay/error";
    }

    @ExceptionHandler(KakaoPayApproveException.class)
    public String kakaoPayApproveExceptionHandler(KakaoPayApproveException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "product/pay/error";
    }

    @ExceptionHandler(KakaoPayBusinessLogicException.class)
    public String kakaoPayBusinessLogicExceptionHandler(KakaoPayBusinessLogicException ex, Model model){;
        model.addAttribute("errorMessage", ex.getMessage());
        return "product/pay/error";
    }

    @ExceptionHandler(KakaoPayRefundException.class)
    public String kakaoPayRefundExceptionHandler(KakaoPayRefundException ex, Model model){;
        model.addAttribute("errorMessage", ex.getMessage());
        return "product/pay/error";
    }
}

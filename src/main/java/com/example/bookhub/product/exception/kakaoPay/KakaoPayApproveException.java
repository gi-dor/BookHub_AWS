package com.example.bookhub.product.exception.kakaoPay;

import com.example.bookhub.product.exception.PayException;

public class KakaoPayApproveException extends PayException {

    public KakaoPayApproveException(String message){
        super(message);
    }
}


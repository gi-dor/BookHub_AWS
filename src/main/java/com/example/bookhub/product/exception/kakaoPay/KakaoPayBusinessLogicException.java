package com.example.bookhub.product.exception.kakaoPay;

import com.example.bookhub.product.exception.PayException;

public class KakaoPayBusinessLogicException extends PayException {

    public KakaoPayBusinessLogicException(String message){
        super(message);
    }
}

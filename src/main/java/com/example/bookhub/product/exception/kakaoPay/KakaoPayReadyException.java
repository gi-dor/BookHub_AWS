package com.example.bookhub.product.exception.kakaoPay;

import com.example.bookhub.product.exception.PayException;

public class KakaoPayReadyException extends PayException {

    public KakaoPayReadyException(String message){
        super(message);
    }
}

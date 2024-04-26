package com.example.bookhub.product.exception.kakaoPay;

import com.example.bookhub.product.exception.PayException;

public class KakaoPayRefundException extends PayException {

    public KakaoPayRefundException(String message){
        super(message);
    }
}

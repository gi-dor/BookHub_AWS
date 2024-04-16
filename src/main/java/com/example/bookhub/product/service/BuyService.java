package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.BuyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final BuyMapper buyMapper;

    public long getBookNoByCartNo(long cartNo){
        return buyMapper.getBookNoByCartNo(cartNo);
    }
}

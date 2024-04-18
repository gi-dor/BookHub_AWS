package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.CartBookDto;
import com.example.bookhub.product.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public List<CartBookDto> findCartList(long userNo){
        return cartMapper.findCartList(userNo);
    }

    public void deleteBookByCartNo(long cartNo){
        cartMapper.deleteBookByCartNo(cartNo);
    }

    public void updateBookCountByCartNo(long cartNo, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("cartNo", cartNo);
        map.put("count", count);
        cartMapper.updateBookCountByCartNo(map);
    }

}

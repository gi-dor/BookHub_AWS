package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.CartMapper;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public List<Book> findCartList(long userNo){
        return cartMapper.findCartList(userNo);
    }

    public void deleteBookByCartNo(long cartNo){
        cartMapper.deleteBookByCartNo(cartNo);
    }

    public void updateBookCountByCartNo(long cartNo, String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("cartNo", cartNo);
        map.put("type", type);
        cartMapper.updateBookCountByCartNo(map);
    }
}

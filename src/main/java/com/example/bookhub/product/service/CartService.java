package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.CartMapper;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

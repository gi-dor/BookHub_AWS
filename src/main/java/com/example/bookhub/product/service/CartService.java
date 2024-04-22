package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.CartBookDto;
import com.example.bookhub.product.mapper.CartMapper;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final UserMapper userMapper;

    public List<CartBookDto> findCartList(String userId){
        User user = userMapper.selectUserById(userId);
        return cartMapper.findCartList(user.getNo());
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

    public String createCart(long bookNo, String userId) {
        User user = userMapper.selectUserById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("bookNo", bookNo);
        map.put("userNo", user.getNo());
        Optional<Long> optional = cartMapper.selectCartNoByBookNoAndUserNo(map);
        if(optional.isEmpty()){
            cartMapper.createCart(map);
            return "new";
        }
        else{
            long cartNo = optional.get();
            cartMapper.increaseBookCountByCartNo(cartNo);
            return "exist";
        }
    }
}

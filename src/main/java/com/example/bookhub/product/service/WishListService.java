package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.WishListMapper;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final UserMapper userMapper;
    private final WishListMapper wishListMapper;

    public void createWishList(long bookNo, String userId) {
        Map map = getUserAndMap(bookNo, userId);
        wishListMapper.createWishList(map);
    }

    public void deleteWishList(long bookNo, String userId) {
        Map map = getUserAndMap(bookNo, userId);
        wishListMapper.deleteWishList(map);
    }

    public Map<String, Object> getUserAndMap(long bookNo, String userId){
        User user = userMapper.selectUserById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("bookNo", bookNo);
        map.put("userNo", user.getNo());
        return map;
    }
}

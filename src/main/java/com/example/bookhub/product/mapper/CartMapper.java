package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.CartBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface CartMapper {

    List<CartBookDto> findCartList(long userNo);
    void deleteBookByCartNo(long cartNo);
    void updateBookCountByCartNo(Map<String, Object> map);
    void createCart(Map<String, Object> map);
    Optional<Long> selectCartNoByBookNoAndUserNo(Map<String, Object> map);
    void increaseBookCountByCartNo(long cartNo);
}

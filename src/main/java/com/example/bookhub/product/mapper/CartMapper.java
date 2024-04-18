package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.CartBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    List<CartBookDto> findCartList(long userNo);
    void deleteBookByCartNo(long cartNo);
    void updateBookCountByCartNo(Map<String, Object> map);
}

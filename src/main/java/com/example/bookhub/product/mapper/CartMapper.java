package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    List<Book> findCartList(long userNo);
    void deleteBookByCartNo(long cartNo);
    void updateBookCountByCartNo(Map<String, Object> map);
}

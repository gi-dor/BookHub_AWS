package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<Book> findCartList(long userNo);
    void deleteBookByCartNo(long cartNo);
}

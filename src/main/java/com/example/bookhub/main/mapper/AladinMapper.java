package com.example.bookhub.main.mapper;

import com.example.bookhub.main.vo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AladinMapper {
    void insertBook(Book book);

}


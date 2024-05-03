package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    BookDto getBookDetailByNo(long bookNo);
    BookDto getBookByBookNo(long bookNo);
}

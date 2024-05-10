package com.example.bookhub.product.mapper;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.vo.BookAuthor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    BookDto getBookDetailByNo(long bookNo);
    BookDto getBookByBookNo(long bookNo);
    void updateBookStatus(long bookNo);
    List<BookAuthor> getAuthorByBookNo(long bookNo);
    void returnBookStock(@Param("bookNo") long bookNo, @Param("count") int count);
}

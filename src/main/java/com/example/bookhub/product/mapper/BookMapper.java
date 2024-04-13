package com.example.bookhub.product.mapper;

import com.example.bookhub.product.vo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    Book getBookDetailByNo(long bookNo);
}

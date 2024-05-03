package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    public BookDto getBookDetailByNo(long bookNo){
        return bookMapper.getBookDetailByNo(bookNo);
    }

    public BookDto getBookByBookNo(long bookNo){
        return bookMapper.getBookByBookNo(bookNo);
    }
}

package com.example.bookhub.product.service;

import com.example.bookhub.product.mapper.BookMapper;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper productMapper;

    public Book findProductDetail(long bookNo){
        return productMapper.findProductDetail(bookNo);
    }
}

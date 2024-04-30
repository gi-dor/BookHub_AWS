package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.ProductFilter;
import com.example.bookhub.admin.mapper.ProductMapper;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public int getTotalRows(ProductFilter filter) {
        return productMapper.getTotalRows(filter);
    }

    public List<BookList> getBooks(ProductFilter filter, int offset, int limit, String sort) {
        return productMapper.getBooks(filter, offset, limit, sort);
    }

    public List<Publisher> getPublishers() {
        return productMapper.getPublishers();
    }
}

package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.mapper.ProductMapper;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public Pagination getPagination(String opt, String keyword, int page, int rows) {
        int totalRows = productMapper.getTotalRows(opt, keyword);
        return new Pagination(page, totalRows, rows);
    }

    public List<BookList> getBooks(String opt, String keyword, int offset, int limit, String sort) {
        return productMapper.getBooks(opt, keyword, offset, limit, sort);
    }

    public List<Publisher> getPublishers() {
        return productMapper.getPublishers();
    }
}

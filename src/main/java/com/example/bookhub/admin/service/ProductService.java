package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.mapper.ProductMapper;
import com.example.bookhub.product.vo.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductMapper productMapper;

    public Pagination getPagination(String opt, String keyword, int page, int rows) {
        // 총 데이터 개수를 조회한다.
        int totalRows = productMapper.getTotalRows(opt, keyword);

        // 페이징 처리에 필요한 정보를 표현하는 객체를 생성한다.
        return new Pagination(page, totalRows, rows);
    }

    public List<Book> getBooks(String opt, String keyword, int offset, int limit) {

        return productMapper.getBooks(opt, keyword, offset, limit);
    }
}

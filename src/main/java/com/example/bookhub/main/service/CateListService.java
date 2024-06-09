package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.BookCategory;
import com.example.bookhub.product.vo.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CateListService {

    private final BookCategory bookCategory;

    public Category getCategory(int categoryNo) {
        return bookCategory.getCategory(categoryNo);
    }

    public BookListDto cateBooks(SearchCriteria criteria) {
        List<BookDto> cateBook = bookCategory.categoryList(criteria);


        int totalRows = bookCategory.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(cateBook);
        dto.setCriteria(criteria);

        return dto;

    }
}

package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.CateListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CateListService {

    private final CateListMapper cateListMapper;

    public BookListDto cateBooks(SearchCriteria criteria) {
        List<BookDto> cateBook = cateListMapper.categoryList(criteria);


        int totalRows = cateListMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(cateBook);
        dto.setCriteria(criteria);

        return dto;

    }
}

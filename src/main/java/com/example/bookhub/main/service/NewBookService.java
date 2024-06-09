package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.NewBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewBookService {
    private final NewBookMapper newBookMapper;

    public BookListDto newBooks(SearchCriteria criteria) {
        List<BookDto> newBook = newBookMapper.newBookList(criteria);

        int totalRows = newBookMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(newBook);
        dto.setCriteria(criteria);

        return dto;
    }

}

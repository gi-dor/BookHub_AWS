package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;



@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {
    private final SearchMapper searchMapper;


    @Override
    public BookListDto searchBooks(SearchCriteria criteria) {
        List<BookDto> books = searchMapper.searchBooks(criteria);

        int totalRows = searchMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(books);
        dto.setCriteria(criteria);

        return dto;
    }

}

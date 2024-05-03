package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.NewBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewBookService {
    private final NewBookMapper newBookMapper;

    public BookListDto newBooks(SearchCriteria criteria) {
        List<BookDto> newBook = newBookMapper.newBookList(criteria);
        System.out.println("000000000000000000000000000000: " + criteria.getCateKeyword());
        for (BookDto book : newBook) {
            // bookDescription이 100글자를 초과하는 경우 처음 100글자만을 남기고 "..."을 추가
            if (book.getDescription() != null && book.getDescription().length() > 100) {
                book.setDescription(book.getDescription().substring(0, 100) + "...");
            }
        }

        int totalRows = newBookMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(newBook);
        dto.setCriteria(criteria);

        return dto;
    }

}

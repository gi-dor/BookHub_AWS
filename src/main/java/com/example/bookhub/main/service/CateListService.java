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
        for (BookDto book : cateBook) {
            // bookDescription이 100글자를 초과하는 경우 처음 100글자만을 남기고 "..."을 추가
            if (book.getDescription() != null && book.getDescription().length() > 100) {
                book.setDescription(book.getDescription().substring(0, 100) + "...");
            }
        }

        int totalRows = cateListMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(cateBook);
        dto.setCriteria(criteria);

        return dto;

    }
}

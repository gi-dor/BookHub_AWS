package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.BestSellerMapper;
import com.example.bookhub.main.mapper.NewBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BestSellerService {
    private final BestSellerMapper bestSellerMapper;

    public BookListDto bestSeller(SearchCriteria criteria) {
        List<BookDto> bestBook = bestSellerMapper.bestSeller(criteria);
        for (BookDto book : bestBook) {
            // bookDescription이 100글자를 초과하는 경우 처음 100글자만을 남기고 "..."을 추가합니다.
            if (book.getDescription() != null && book.getDescription().length() > 100) {
                book.setDescription(book.getDescription().substring(0, 100) + "...");
            }
        }

        int totalRows = bestSellerMapper.count(criteria);
        criteria.setTotalRows(totalRows);

        BookListDto dto = new BookListDto();
        dto.setBooks(bestBook);
        dto.setCriteria(criteria);

        return dto;
    }

}

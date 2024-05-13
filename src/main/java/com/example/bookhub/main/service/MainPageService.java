package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.MainPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainPageService {
    private final MainPageMapper mainPageMapper;

    @Transactional(readOnly = true)
    public BookListDto mainBestSeller(SearchCriteria criteria) {
        List<BookDto> mainBest = mainPageMapper.getBestSeller(criteria);

        BookListDto dto = new BookListDto();
        dto.setBooks(mainBest);
        dto.setCriteria(criteria);

        return dto;
    }

    @Transactional(readOnly = true)
    public BookListDto mainNewBook(SearchCriteria criteria) {
        List<BookDto> mainNew = mainPageMapper.getNewBook(criteria);

        BookListDto dto = new BookListDto();
        dto.setBooks(mainNew);
        dto.setCriteria(criteria);

        return dto;
    }

    @Transactional(readOnly = true)
    public BookListDto mainBookViewCount(SearchCriteria criteria) {
        List<BookDto> mainViewCount = mainPageMapper.getBookViewCount(criteria);

        BookListDto dto = new BookListDto();
        dto.setBooks(mainViewCount);
        dto.setCriteria(criteria);

        return dto;
    }
    @Transactional(readOnly = true)
    public BookListDto mainBookRecommend(SearchCriteria criteria) {
        List<BookDto> mainBookReco = mainPageMapper.getRecoBook(criteria);

        BookListDto dto = new BookListDto();
        dto.setBooks(mainBookReco);
        dto.setCriteria(criteria);

        return dto;
    }


}

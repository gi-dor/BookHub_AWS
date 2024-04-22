package com.example.bookhub.main.service;

import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.bookhub.main.vo.Book;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchMapper searchMapper;

    @Override
    public List<Book> searchBooks(SearchCriteria searchCriteria) {
       return searchMapper.searchBooks(searchCriteria);
    }

}

package com.example.bookhub.main.mapper;

import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.vo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Book> searchBooks(SearchCriteria criteria);

    int getTotalBooks(SearchCriteria criteria);
}

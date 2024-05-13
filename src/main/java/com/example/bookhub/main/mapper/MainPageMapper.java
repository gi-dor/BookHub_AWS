package com.example.bookhub.main.mapper;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainPageMapper {
    List<BookDto> getBestSeller(SearchCriteria criteria);
    List<BookDto> getNewBook(SearchCriteria criteria);
    List<BookDto> getBookViewCount(SearchCriteria criteria);
    List<BookDto> getRecoBook(SearchCriteria criteria);

}

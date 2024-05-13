package com.example.bookhub.main.mapper;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RecommendBookMapper {
    /**
     * 추천책 리스트 조회
     * @return 결과 리스트
     */
    List<BookDto> recoBookList(SearchCriteria criteria);

    /**
     *
     * 검색 수 카운팅
     * @return 검색 수
     */
    int count(SearchCriteria criteria);
}

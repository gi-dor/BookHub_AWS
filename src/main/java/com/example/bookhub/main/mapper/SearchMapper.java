package com.example.bookhub.main.mapper;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    /**
     * 검색 리스트 조회
     * @return 검색결과 리스트
     */
    List<BookDto> searchBooks(SearchCriteria criteria);

    /**
     *
     * 검색 수 카운팅
     * @return 검색 수
     */
    int count(SearchCriteria criteria);

}

/*paging이 안되었던 이유는
 *  List<Book> searchBooks(@Param("criteria") SearchCriteria criteria);
 *  List<Book> searchBooks(@Param("criteria") SearchCriteria criteria);
 * 굳이 안붙여도될 @Param을 붙였기 때문이다. 그래서
 *org.apache.ibatis.binding.BindingException: Parameter 'opt' not found. Available parameters are [criteria, param1] 이 오류가 뜸
 *  */

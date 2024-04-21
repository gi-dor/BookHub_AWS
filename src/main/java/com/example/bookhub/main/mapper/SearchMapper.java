package com.example.bookhub.main.mapper;

import com.example.bookhub.main.vo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    /*
     상품 검색
     DB에서 조회된 정보를 리스트에 담는다.
     List<Book> 형태로 반환
    */
    List<Book> getSearchList(String keyword);


    /*상품 총 개수*/
     int bookGetTotal();

}

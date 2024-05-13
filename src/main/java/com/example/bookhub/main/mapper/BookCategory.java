
package com.example.bookhub.main.mapper;

import com.example.bookhub.main.dto.BookDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.product.vo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BookCategory {
    /**
     * 각각의 카테고리에대한 리스트 조회
     * @return
     */
    List<BookDto> categoryList(SearchCriteria criteria);

    /**
     *
     * 검색 수 카운팅
     * @return 검색 수
     */
    int count(SearchCriteria criteria);

    Category getCategory(int categoryNo);

}

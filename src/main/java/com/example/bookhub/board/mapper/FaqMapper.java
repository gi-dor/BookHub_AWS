package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Faq;
import com.example.bookhub.board.vo.FaqCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaqMapper {

    /**
     * 페이징 처리된 FAQ 목록 조회
     * @param offset
     * @param size
     * @return
     */
    List<Faq> findAllFaq(@Param("offset") int offset, @Param("size") int size);

    /**
     * 전체 FAQ 개수 조회
     * @return
     */
    int getTotalFaqCount();


    /**
     * FAQ카테고리의 번호로 FAQ카테고리를 조회
     * @param no 카테고리의 번호
     * @return
     */
    FaqCategory getFaqCategoryByNo(Long no);

    Faq getFaqByNo(Long no);
}

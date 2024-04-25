package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Faq;
import com.example.bookhub.board.vo.FaqCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FaqMapper {

    /**
     * 페이징 처리된 faq 리스트 조회
     * @param keyword
     * @param offset
     * @return
     */
    List<Faq> findAllFaq(@Param("cat") int cat, @Param("keyword") String keyword, @Param("offset") int offset);

    /**
     * faq list의 전체 개수 조회
     * @param keyword
     * @return
     */
    int getTotalFaqCount(@Param("cat") int cat, @Param("keyword") String keyword);

    /**
     * FAQ카테고리의 번호로 FAQ카테고리를 조회
     * @param no 카테고리의 번호
     * @return
     */
    FaqCategory getFaqCategoryByNo(Long no);

    /**
     * FAQ를 번호로 상세조회
     * @param no
     * @return
     */
    Faq getFaqByNo(Long no);

    /**
     * FAQ Category 전체 조회
     * @return
     */
    List<FaqCategory> findAllCategories();

}

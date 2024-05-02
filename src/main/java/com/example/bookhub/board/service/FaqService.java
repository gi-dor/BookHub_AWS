package com.example.bookhub.board.service;

import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.dto.FaqListDto;
import com.example.bookhub.board.mapper.FaqMapper;
import com.example.bookhub.board.vo.Faq;
import com.example.bookhub.board.vo.FaqCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * 페이징 처리된 전체 리스트 혹은 검색 키워드 리스트 조회
     * @param keyword
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public FaqListDto getFaqList(int cat, String keyword, int page) {

        int totalCount = faqMapper.getTotalFaqCount(cat, keyword);
        Pagination pagination = new Pagination(page, totalCount);
        int offset = pagination.getBegin();

        List<Faq> faqs = faqMapper.findAllFaq(cat, keyword, offset);

        return new FaqListDto(faqs, pagination);
    }


    /**
     *  카테고리 번호를 불러와 카테고리의 번호와 이름을 조회
     * @param categoryNo
     * @return
     */
    @Transactional(readOnly = true)
    public FaqCategory getFaqCategoryByNo(Long categoryNo) {
        return faqMapper.getFaqCategoryByNo(categoryNo);
    }

    /**
     * FAQ의 상세 조회
     * @param no
     * @return
     */
    @Transactional(readOnly = true)
    public Faq getFaqByNo(Long no) {
        return faqMapper.getFaqByNo(no);
    }

    /**
     * FAQ category 전체 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<FaqCategory> findAllCategories() {
        return faqMapper.findAllCategories();
    }

    
}

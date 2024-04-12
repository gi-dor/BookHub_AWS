package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.FaqMapper;
import com.example.bookhub.board.vo.Faq;
import com.example.bookhub.board.vo.FaqCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * 페이징 처리된 FAQ 목록 조회
     * @param page
     * @param size
     * @return
     */
    public List<Faq> findAllFaq(int page, int size) {
        int offset = (page - 1) * size;
        return faqMapper.findAllFaq(offset, size);
    }

    public int getTotalFaqCount() {
        return faqMapper.getTotalFaqCount();
    }





    /**
     *  카테고리 번호를 불러와 카테고리의 번호와 이름을 조회
     * @param categoryNo
     * @return
     */
    public FaqCategory getFaqCategoryByNo(Long categoryNo) {
        return faqMapper.getFaqCategoryByNo(categoryNo);
    }

}

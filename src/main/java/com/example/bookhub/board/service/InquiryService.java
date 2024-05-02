package com.example.bookhub.board.service;

import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.dto.InquiryListDto;
import com.example.bookhub.board.mapper.FaqMapper;
import com.example.bookhub.board.mapper.InquiryMapper;
import com.example.bookhub.board.vo.FaqCategory;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final UserMapper userMapper;
    private final FaqMapper faqMapper;
    private final InquiryMapper inquiryMapper;

    /**
     * 페이징 처리된 문의사항 리스트 조회
     * @param page
     * @param no
     * @return
     */
    public InquiryListDto findAllInquiry(int page, long no) {

        int totalCount = inquiryMapper.getTotalInquiry(no);
        Pagination pagination = new Pagination(page, totalCount);
        int offset = pagination.getBegin()-1;

        List<Inquiry> inquiries = inquiryMapper.findAllInquiry(offset, no);

        return new InquiryListDto(inquiries, pagination);
    }


    /**
     * 문의사항 등록
     * @param title
     * @param content
     * @param userId
     * @param catNo
     */
    public void insertInquiry(String title, String content, String userId, long catNo) {

        User user = userMapper.selectUserById(userId);
        FaqCategory faqCategory = faqMapper.getFaqCategoryByNo(catNo);

        Inquiry inquiry = new Inquiry();
        inquiry.setTitle(title);
        inquiry.setContent(content);
        inquiry.setFaqCategory(faqCategory);
        inquiry.setUser(user);

        inquiryMapper.insertInquiry(inquiry);

    }

    /**
     * 문의글 번호로 문의 조회
     * @param no
     * @return
     */
    public Inquiry getInquiryByNo(long no) {
        return inquiryMapper.getInquiryByNo(no);
    }

    public List<InquiryComment> getInquiryComment(long no) {
        return inquiryMapper.getInquiryComment(no);
    }

    /**
     * 문의글 수정
     * @param inquiry
     */
    public void modifyInquiry(Inquiry inquiry) {
        inquiryMapper.modifyInquiry(inquiry);
    }

    /**
     * 문의글 삭제
     * @param no
     */
    public void deleteInquiry(long no) {
        inquiryMapper.deleteInquiry(no);
    }



























}

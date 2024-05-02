package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryMapper {


    /**
     * 페이징 처리된 문의사항 조회
     * @param offset
     * @param no
     * @return
     */
    List<Inquiry> findAllInquiry(@Param("offset") int offset, @Param("no") long no);

    /**
     * 문의사항 개수 조회
     * @return
     */
    int getTotalInquiry(long no);

    /**
     * 문의사항 등록
     * @param inquiry
     */
    void insertInquiry(Inquiry inquiry);

    /**
     * 문의글 번호로 문의 조회
     * @param no
     * @return
     */
    Inquiry getInquiryByNo(long no);

    /**
     * 문의글 번호로 답변 조회
     * @param no
     * @return
     */
    List<InquiryComment> getInquiryComment(long no);

    /**
     * 문의글 수정
     * @param inquiry
     */
    void modifyInquiry(Inquiry inquiry);

    void deleteInquiry(long no);

}

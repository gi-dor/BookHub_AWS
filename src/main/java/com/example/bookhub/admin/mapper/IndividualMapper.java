package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IndividualMapper {

    // 답변 미완료된 리스트 및 디테일
    List<IndividualDto> getNoAnswerList(@Param("pagination") Pagination pagination);
    Inquiry getNoAnswerNo(Long no);

    // 전체 행 수를 구하는 매퍼
    int getTotalRows();

    // 답변 및 답변했을 때 답변유뮤를 변경
    void insertAnswer(InquiryComment inquiryComment);
    void updateNoAnswer(Long no);

    // 답변 완료된 리스트 및 디테일
    List<IndividualDto> getAnswerList(@Param("pagination") Pagination pagination);
    InquiryComment getAnswerNo(Long no);    // 질문번호로 조회

    // 답변번호로 조회
    InquiryComment getAnswerResponseNo(Long no);

    // 답변 수정
    void updateAnswer(InquiryComment inquiryComment);
}

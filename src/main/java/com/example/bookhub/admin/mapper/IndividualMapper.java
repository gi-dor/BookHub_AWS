package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndividualMapper {

    List<IndividualDto> getNoAnswerList();
    Inquiry getNoAnswerNo(Long no);

    void insertAnswer(InquiryComment inquiryComment);
    void updateNoAnswer(Long no);

    List<IndividualDto> getAnswerList();
    Inquiry getAnswerNo(Long no);

}

package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.admin.exception.NoAdminLoginException;
import com.example.bookhub.admin.mapper.IndividualMapper;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualService {

    private final IndividualMapper individualMapper;

    // 미완료 CS 시작
    public List<IndividualDto> getNoAnswerList(){
        return individualMapper.getNoAnswerList();
    }

    public Inquiry getNoAnswerNo(Long no){
        return individualMapper.getNoAnswerNo(no);
    }

    // 답변하는 서비스
    public void insertAnswer(Long no, String content, Admin admin){
        Inquiry inquiry = individualMapper.getNoAnswerNo(no);

        InquiryComment inquiryComment = new InquiryComment();
        inquiryComment.setAdmin(admin);
        inquiryComment.setInquiry(inquiry);
        inquiryComment.setComment(content);

        individualMapper.insertAnswer(inquiryComment);
        individualMapper.updateNoAnswer(no);
    }
    // 미완료 CS 끝

    // 완료 CS
    public List<IndividualDto> getAnswerList(){
        return individualMapper.getAnswerList();
    }

    public InquiryComment getAnswerNo(Long no){
        return individualMapper.getAnswerNo(no);
    }
}

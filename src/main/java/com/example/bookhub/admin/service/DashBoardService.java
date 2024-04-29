package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.ratioDto;
import com.example.bookhub.admin.mapper.DashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DashBoardMapper dashBoardMapper;

    public int getAllUserCnt() {
        return dashBoardMapper.getAllUserCnt();
    }

    public int getAllBookCnt(){
        return dashBoardMapper.getAllBookCnt();
    }

    public List<DayTotalDto> getTotalDate() {
        return dashBoardMapper.getTotalDate();
    }

    public DayTotalDto getYesterDay(String value){
        return dashBoardMapper.getYesterDay(value);
    }

    public int noAnswerCnt(){
        return dashBoardMapper.noAnswerCnt();
    }

    public int answerCnt(){
        return dashBoardMapper.answerCnt();
    }

    public List<ReviewDto> getReviews(){
        return dashBoardMapper.getReview();
    }

    public double averageRate(){
        return dashBoardMapper.averageRate();
    }

    public int noAnswerRatio(){
        return dashBoardMapper.noAnswerRatio();
    }

    public int answerRatio(){
        return dashBoardMapper.answerRatio();
    }

    public List<ratioDto> getRatios() {
        return dashBoardMapper.getRatio();
    }

}

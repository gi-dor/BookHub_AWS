package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.RatioDto;
import com.example.bookhub.admin.mapper.DashBoardMapper;
import com.example.bookhub.admin.mapper.DetailDashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DashBoardMapper dashBoardMapper;
    private final DetailDashBoardMapper detailDashBoardMapper;

    @Transactional(readOnly = true)
    public int getAllUserCnt() {
        return dashBoardMapper.getAllUserCnt();
    }

    @Transactional(readOnly = true)
    public int getAllBookCnt(){
        return dashBoardMapper.getAllBookCnt();
    }

    @Transactional(readOnly = true)
    public List<DayTotalDto> getTotalDate() {
        return dashBoardMapper.getTotalDate();
    }

    @Transactional(readOnly = true)
    public DayTotalDto getDayTotal(String value){
       DayTotalDto dto = dashBoardMapper.getDayTotal(value);
       List<DailyDto> item = detailDashBoardMapper.getDetailDaily(value);
       dto.setItems(item);

       return dto;
    }

    @Transactional(readOnly = true)
    public int noAnswerCnt(){
        return dashBoardMapper.noAnswerCnt();
    }

    @Transactional(readOnly = true)
    public int answerCnt(){
        return dashBoardMapper.answerCnt();
    }

    @Transactional(readOnly = true)
    public List<ReviewDto> getReviews(){
        return dashBoardMapper.getReview();
    }

    @Transactional(readOnly = true)
    public float averageRate(){
        return dashBoardMapper.averageRate();
    }

    @Transactional(readOnly = true)
    public int noAnswerRatio(){
        return dashBoardMapper.noAnswerRatio();
    }

    @Transactional(readOnly = true)
    public int answerRatio(){
        return dashBoardMapper.answerRatio();
    }

    @Transactional(readOnly = true)
    public List<RatioDto> getRatios() {
        return dashBoardMapper.getRatio();
    }

}

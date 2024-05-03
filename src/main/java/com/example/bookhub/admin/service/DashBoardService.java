package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.RatioDto;
import com.example.bookhub.admin.mapper.DashBoardMapper;
import com.example.bookhub.admin.mapper.DetailDashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DashBoardMapper dashBoardMapper;
    private final DetailDashBoardMapper detailDashBoardMapper;

    public int getAllUserCnt() {
        return dashBoardMapper.getAllUserCnt();
    }

    public int getAllBookCnt(){
        return dashBoardMapper.getAllBookCnt();
    }

    public List<DayTotalDto> getTotalDate() {
        return dashBoardMapper.getTotalDate();
    }

    public DayTotalDto getDayTotal(String value){
       DayTotalDto dto = dashBoardMapper.getDayTotal(value);
       List<DailyDto> item = detailDashBoardMapper.getDetailDaily(value);
       dto.setItems(item);

       return dto;
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

    public float averageRate(){
        return dashBoardMapper.averageRate();
    }

    public int noAnswerRatio(){
        return dashBoardMapper.noAnswerRatio();
    }

    public int answerRatio(){
        return dashBoardMapper.answerRatio();
    }

    public List<RatioDto> getRatios() {
        return dashBoardMapper.getRatio();
    }

}

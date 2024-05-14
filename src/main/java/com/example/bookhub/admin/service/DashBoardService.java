package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.RatioDto;
import com.example.bookhub.admin.mapper.DashBoardMapper;
import com.example.bookhub.admin.mapper.DetailDashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DashBoardMapper dashBoardMapper;
    private final DetailDashBoardMapper detailDashBoardMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.getAllUserCnt") // 1시간에 한번 캐싱, 회원 수 가져오기
    public int getAllUserCnt() {
        return dashBoardMapper.getAllUserCnt();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.getAllBookCnt") // 1시간에 한번 캐싱, 총 책의 권 수 가져오기
    public int getAllBookCnt(){
        return dashBoardMapper.getAllBookCnt();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.getTotalDate") // 12시간에 한번 캐싱, 7일치의 매출 표시
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
    @Cacheable(value = "dashBoardMapper.noAnswerCnt") // 1시간에 한번 캐싱
    public int noAnswerCnt(){
        return dashBoardMapper.noAnswerCnt();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.answerCnt") // 1시간에 한번 캐싱
    public int answerCnt(){
        return dashBoardMapper.answerCnt();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.getReviews") // 1시간에 한번 캐싱
    public List<ReviewDto> getReviews(){
        return dashBoardMapper.getReview();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.averageRate") // 1시간에 한번 캐싱
    public float averageRate(){
        return dashBoardMapper.averageRate();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.noAnswerRatio") // 1시간에 한번 캐싱
    public int noAnswerRatio(){
        return dashBoardMapper.noAnswerRatio();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.answerRatio") // 1시간에 한번 캐싱
    public int answerRatio(){
        return dashBoardMapper.answerRatio();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "dashBoardMapper.getRatios") // 1시간에 한번 캐싱
    public List<RatioDto> getRatios() {
        return dashBoardMapper.getRatio();
    }

}

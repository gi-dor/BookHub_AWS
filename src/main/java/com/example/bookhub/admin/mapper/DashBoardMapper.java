package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.ratioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashBoardMapper {
    // 총 유저 수, 책의 총 권 수
    int getAllUserCnt();
    int getAllBookCnt();

    // 통게 테이블 값 가져오기
    List<DayTotalDto> getTotalDate();
    DayTotalDto getYesterDay(String value);

    // 답변이 완료되지 않은 문의 건수
    int noAnswerCnt();
    int answerCnt();

    // 리뷰 5개 갖고오기, 평균 평점
    List<ReviewDto> getReview();
    float averageRate();

    // 미답변률, 답변률
    int noAnswerRatio();
    int answerRatio();

    // 답변미답변 그래프
    List<ratioDto> getRatio();



}

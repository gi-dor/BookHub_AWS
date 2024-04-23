package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.DayTotalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashBoardMapper {
    // 총 유저 수, 책의 총 권 수
    int getAllUserCnt();
    int getAllBookCnt();

    // 어제 날짜의 합계값을 통계 테이블 저장
    void saveTotalYesterday();
    // 지난 주(일요일~토요일) 값을 통계 테이블에 저장
    void saveTotalLastWeek();

    // 통게 테이블 값 가져오기
    List<DayTotalDto> getTotalDate();

}

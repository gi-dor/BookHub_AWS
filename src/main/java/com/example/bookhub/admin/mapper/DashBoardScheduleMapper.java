package com.example.bookhub.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashBoardScheduleMapper {


    // 어제 날짜의 합계값을 통계 테이블 저장
    void saveTotalYesterday();
    // 지난 주(일요일~토요일) 값을 통계 테이블에 저장
    void saveTotalLastWeek();
    // 지난달 매출액을 통계 테이블에 저장
    void saveTotalLastMonth();

}

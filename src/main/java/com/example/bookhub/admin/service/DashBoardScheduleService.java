package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.DashBoardScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardScheduleService {

    private final DashBoardScheduleMapper dashBoardScheduleMapper;

    // 매일 아침 10시마다 어제 통계값을 계산하여 저장하기
    @Scheduled(cron = "0 0 10 * * ?")
    public void saveTotalYesterday() {
        dashBoardScheduleMapper.saveTotalYesterday();
    }

    // 매주 월요일 10시마다 지난주 값을 계산하여 저장하기
    @Scheduled(cron = "0 00 10 * * MON")
    public void saveTotalLastWeek() {dashBoardScheduleMapper.saveTotalLastWeek();}

    // 매월 1일 오전 12시마다 지난달 값을 계산하여 저장하기
    @Scheduled(cron = "0 0 10 2 * ?")
    public void saveTotalLastMonth(){
        dashBoardScheduleMapper.saveTotalLastMonth();
    }
}

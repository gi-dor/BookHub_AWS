package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.DashBoardScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DashBoardScheduleService {

    private final DashBoardScheduleMapper dashBoardScheduleMapper;

    //SchedulerLock
    // name : LockName으로 사용할 값. 중복 X
    // lockAtLeastFor : Lock이 유지되는 최소시간 ( 수행시간이 매우 빠를 때 중복실행이 일어나는 문제를 방지할 수 있다)
    // lockAtMostFor : Lock이 유지되는 최대시간 ( 수행시간이 길어지거나, 끝나지 않을 때 다음 순서가 실행되지 않는 문제를 방지할 수 있다)
    // LockAtLeastFor 의 값이 LockAtMostFor 값보다 크면 Exception 이 발생

    // 매일 아침 10시마다 어제 통계값을 계산하여 저장하기
    @SchedulerLock(name = "yesterday_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = "0 0 10 * * ?")
    public void saveTotalYesterday() {
        dashBoardScheduleMapper.saveTotalYesterday();
    }

    // 매주 월요일 10시마다 지난주 값을 계산하여 저장하기
    @SchedulerLock(name = "lastWeek_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = "0 00 10 * * MON")
    public void saveTotalLastWeek() {dashBoardScheduleMapper.saveTotalLastWeek();}

    // 매월 1일 오전 12시마다 지난달 값을 계산하여 저장하기
    @SchedulerLock(name = "lastMonth_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = "0 0 12 1 * ?")
    public void saveTotalLastMonth(){
        dashBoardScheduleMapper.saveTotalLastMonth();
    }
}

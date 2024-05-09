package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.DashBoardScheduleMapper;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


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
        retryLogic(dashBoardScheduleMapper::saveTotalYesterday);
    }

    // 매주 월요일 10시마다 지난주 값을 계산하여 저장하기
    @SchedulerLock(name = "lastWeek_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = "0 00 10 * * MON")
    public void saveTotalLastWeek() {
        retryLogic(dashBoardScheduleMapper::saveTotalYesterday);
    }

    // 매월 1일 오전 12시마다 지난달 값을 계산하여 저장하기
    @SchedulerLock(name = "lastMonth_lock", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    @Scheduled(cron = "0 0 12 1 * ?")
    public void saveTotalLastMonth(){
        retryLogic(dashBoardScheduleMapper::saveTotalLastMonth);
    }

    private void retryLogic(Runnable task) {
        int maxAttempts = 3; // 최대 시도 횟수
        int attempts = 0;
        boolean success = false;

        while (attempts < maxAttempts) {
            try {
                // 작업 실행하고 작업이 성공하면 while문 탈출
                task.run();
                success = true;
                break;
            } catch (Exception ex){
                attempts ++;
                System.out.println("작업을 재시도합니다. 현재 시도횟수 : " + attempts);
            }
        }

        if(!success) {
            // 최대 시도 횟수를 넘겼을 때 실행된다
            System.out.println("작업을 최대 시도 횟수를 초과하여 실패했습니다");

        }
    }
}

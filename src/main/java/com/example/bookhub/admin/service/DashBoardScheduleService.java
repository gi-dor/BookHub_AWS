package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.DashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardScheduleService {

    private final DashBoardMapper dashBoardMapper;

    @Scheduled(cron = "0 30 9 * * ?")
    public void saveTotalYesterday() {
        dashBoardMapper.saveTotalYesterday();
    }
}

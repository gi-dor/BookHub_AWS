package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.*;
import com.example.bookhub.admin.mapper.DetailDashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailDashBoardService {

    private final DetailDashBoardMapper detailDashBoardMapper;


    public List<DailyDto> getDetailDaily(String value){
        return detailDashBoardMapper.getDetailDaily(value);
    }

    public List<DetailPercentDto> getDetailPercent(String searchData){
        return detailDashBoardMapper.getDetailPercent(searchData);
    }

    public DayRangeDto getDetailRange(String startDate, String endDate) {
        DayRangeDto dto = new DayRangeDto();

        List<DayRangeStat> stats = detailDashBoardMapper.getDayRangeStats(startDate, endDate);
        List<DayRangeItem> items = detailDashBoardMapper.getDayRangeItems(startDate, endDate);

        dto.setStats(stats);
        dto.setItems(items);

        return dto;
    }

    public List<DetailPercentDto> getRangePercent(String startDate, String endDate){
        return detailDashBoardMapper.getRangePercent(startDate, endDate);
    }
}

package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.DetailPercentDto;
import com.example.bookhub.admin.mapper.DetailDashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<DayTotalDto> getDetailRange(String startDate, String endDate) {
        List<DayTotalDto> dtoList = detailDashBoardMapper.getDetailRange(startDate, endDate);

        // 각각의 DayTotalDto 객체에 DailyDto 리스트를 설정합니다.
        for (DayTotalDto dto : dtoList) {
            List<DailyDto> item = detailDashBoardMapper.getDetailRangeItem(startDate, endDate);
            dto.setItems(item);
        }

        return dtoList;
    }
}

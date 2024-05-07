package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailDashBoardMapper {


    List<DailyDto> getDetailDaily(String value);
    List<DetailPercentDto> getDetailPercent(String searchData);


    List<DayRangeStat> getDayRangeStats(String startDate, String endDate);
    List<DayRangeItem> getDayRangeItems(String startDate, String endDate);

    List<DetailPercentDto> getRangePercent(String startDate, String endDate);
}

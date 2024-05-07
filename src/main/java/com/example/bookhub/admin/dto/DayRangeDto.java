package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayRangeDto {

    private List<DayRangeStat> stats;
    private List<DayRangeItem> items;

}

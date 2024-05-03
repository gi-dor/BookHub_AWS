package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayTotalDto {

    private String dayTotalDate;
    private int dayTotalPrice;
    private List<DailyDto> items;
}

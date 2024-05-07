package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.dto.DayRangeDto;
import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.DetailPercentDto;
import com.example.bookhub.admin.service.DetailDashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dash")
public class DetailDashBoardController {

    private final DetailDashBoardService detailDashBoardService;

    @GetMapping("/dailyDash")
    public String dailyDash(){

        return "/admin/dash/dailyDash";
    }

    @RequestMapping("/getDetailPercent.do")
    public @ResponseBody List<DetailPercentDto> getDetailPercent(String searchData){
        return detailDashBoardService.getDetailPercent(searchData);
    }


    @GetMapping("/rangeDash")
    public String weekDash(){
        return "/admin/dash/weekDash";
    }

    @RequestMapping("/getDetailRange.do")
    public @ResponseBody DayRangeDto getDetailRange(String startDate, String endDate){
        return detailDashBoardService.getDetailRange(startDate, endDate);
    }

    @RequestMapping("/getRangePercent.do")
    public @ResponseBody List<DetailPercentDto> getRangePercent(String startDate, String endDate){
        return detailDashBoardService.getRangePercent(startDate, endDate);
    }

    
}

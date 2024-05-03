package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.DailyDto;
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

    
}

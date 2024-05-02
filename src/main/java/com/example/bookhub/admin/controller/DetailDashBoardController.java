package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.DailyDto;
import com.example.bookhub.admin.service.DetailDashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dash")
public class DetailDashBoardController {

    private final DetailDashBoardService detailDashBoardService;

    @GetMapping("/dailyDash")
    public String dailyDash(){

        return "/admin/dash/dailyDash";
    }

    
}

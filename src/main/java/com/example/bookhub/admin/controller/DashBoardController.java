package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.DayTotalDto;
import com.example.bookhub.admin.dto.ratioDto;
import org.springframework.ui.Model;
import com.example.bookhub.admin.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dash")
public class DashBoardController {

    private final DashBoardService dashBoardService;


    @GetMapping("/dailyDash")
    public String dailyDash(){
        return "/admin/dash/dailyDash";
    }

    @GetMapping("/weekDash")
    public String weekDash(){
        return "/admin/dash/weekDash";
    }


    // 총 회원 수
    @RequestMapping("/allCustomer.do")
    public @ResponseBody Map<String, Object> getAllUserCnt(Model model){
        int cnt = dashBoardService.getAllUserCnt();

        Map<String, Object> map = new HashMap<>();
        map.put("allCustomer", cnt);
        model.addAttribute("allCustomer", cnt);
        return map;

    }

    // 총 책의 권 수
    @RequestMapping("/getAllBookCnt.do")
    public @ResponseBody Map<String, Object> getAllBookCnt(Model model){
        int cnt = dashBoardService.getAllBookCnt();
        Map<String, Object> map = new HashMap<>();
        map.put("allBook", cnt);
        model.addAttribute("allBook", cnt);
        return map;
    }

    // 최근 7일치의 매출 현황
    @RequestMapping("/getTotalYesterday.do")
    public @ResponseBody List<DayTotalDto> getTotalYesterday(){
        return dashBoardService.getTotalDate();
    }

    @RequestMapping("/getYesterDay.do")
    public @ResponseBody DayTotalDto getYesterDay(String value){
        return dashBoardService.getYesterDay(value);
    }

    @RequestMapping("/noAnswerCnt.do")
    public @ResponseBody Map<String, Object> getUserCnt(Model model){
        int cnt = dashBoardService.noAnswerCnt();
        Map<String, Object> map = new HashMap<>();
        map.put("noAnswer", cnt);
        model.addAttribute("noAnswer", cnt);
        return map;
    }

    @RequestMapping("/ratio.do")
    public @ResponseBody List<ratioDto> getRatio(){
        return dashBoardService.getRatios();
    }
}

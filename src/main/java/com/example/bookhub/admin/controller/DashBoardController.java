package com.example.bookhub.admin.controller;

import com.example.bookhub.user.vo.User;
import org.springframework.ui.Model;;
import com.example.bookhub.admin.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashBoardController {

    private final DashBoardService dashBoardService;
    private final RestTemplate restTemplate;


    @RequestMapping("/allCustomer.do")
    public @ResponseBody Map<String, Object> getAllUserCnt(Model model){
        int cnt = dashBoardService.getAllUserCnt();

        Map<String, Object> map = new HashMap<>();
        map.put("allCustomer", cnt);
        model.addAttribute("allCustomer", cnt);
        return map;

    }
}

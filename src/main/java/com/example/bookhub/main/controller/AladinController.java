package com.example.bookhub.main.controller;

import com.example.bookhub.main.service.AladinService;
import com.example.bookhub.main.vo.Aladin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class AladinController {
    private final AladinService aladinService;



    //책 정보를 받아와서 mysql에 저장하는 요청 처리
    @GetMapping("/aladin")
    @ResponseBody
    public Aladin getAladinResponse() {
        aladinService.fetchItemsFromAladin();
        return null;
    }

}





package com.example.bookhub.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage()  {
        return "main";
    }


    @GetMapping("/navbar")
    public String showNavbar() {
        return "navbar"; // navbar.html 파일의 경로를 반환
    }
    @GetMapping("/main1")
    public String mainPage2() {
        return "main1"; // navbar.html 파일의 경로를 반환
    }

    }





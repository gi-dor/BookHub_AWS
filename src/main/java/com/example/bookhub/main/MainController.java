package com.example.bookhub.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage()  {
        return "main";
    }

    @Controller
    public class NavbarController {

        @GetMapping("/navbar1")
        public String showNavbar() {
            return "navbar1"; // navbar.html 파일의 경로를 반환
        }
    }
}

package com.example.bookhub.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }


    @GetMapping("/navbar")
    public String Navbar() {
        return "navbar";
    }

    @GetMapping("/main2")
    public String mainPage2() {
        return "main/main2";
    }

    @GetMapping("/list")
    public String bookList() {
        return "main/list";
    }

}







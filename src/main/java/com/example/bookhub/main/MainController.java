package com.example.bookhub.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main/main2";
    }


    @GetMapping("/navbar")
    public String Navbar() {
        return "navbar";
    }


    @GetMapping("/list")
    public String BestSellerList() {
        return "catelist";
    }

    @GetMapping("/searchlist")
    public String searchList() {
        return "main/searchList";
    }
}







package com.example.bookhub.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @GetMapping("/home")
    public String home(){
        return "admin/home";
    }

    @GetMapping("/sidebar")
    public String sidebartest(){
        return "admin/sidebar";
    }
}

package com.example.bookhub.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/list")
    public String product() {
        return "admin/product/product_list";
    }
}

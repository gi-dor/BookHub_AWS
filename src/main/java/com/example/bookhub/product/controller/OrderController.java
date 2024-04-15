package com.example.bookhub.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product/buy")
public class OrderController {

    @PostMapping("/order")
    public String getOrder(@RequestParam("selectedBook") String[] selectedBookList){
        for (String selectedBook : selectedBookList) {
            //System.out.println("Selected Book No: " + selectedBook);
        }
        return "product/buy/order";
    }
}

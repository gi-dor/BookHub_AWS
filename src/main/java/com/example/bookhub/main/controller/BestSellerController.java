package com.example.bookhub.main.controller;

import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.service.BestSellerService;
import com.example.bookhub.main.service.NewBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BestSellerController {


    private final BestSellerService bestSellerService;

    @GetMapping("/main/bestseller")
    public String bestSeller(SearchCriteria searchCriteria, Model model) {
        BookListDto dto = bestSellerService.bestSeller(searchCriteria);
        model.addAttribute("book", dto.getBooks());
        model.addAttribute("criteria", dto.getCriteria());
        return "/main/bestseller.html";
    }
    }



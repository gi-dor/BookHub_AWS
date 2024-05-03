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


    /*
     @GetMapping("/main/bestseller")
    public String BestSeller(
            @RequestParam(name="period", required=false, defaultValue = "0") int period,
            @RequestParam(name="cateKeyword", required=false, defaultValue= "0") int cateKeyword,
            @RequestParam(name="sort", required=false, defaultValue= "1") int sort,
            @RequestParam(name="page", required=false, defaultValue= "1") int page,
            Model model) { // Model 객체를 메서드 파라미터로 추가
        SearchCriteria searchCriteria = new SearchCriteria(); // 검색 조건 생성
        searchCriteria.setCateKeyword(cateKeyword);
        searchCriteria.setSort(sort);
        searchCriteria.setPage(page);
        searchCriteria.setPeriod(period);

        BookListDto dto = bestSellerService.bestSeller(searchCriteria);
        model.addAttribute("book", dto.getBooks()); // 모델에 책 정보를 담아서 HTML로 전달
        model.addAttribute("criteria", dto.getCriteria());
        return "/main/bestseller.html";
    }
    * */
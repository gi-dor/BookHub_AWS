package com.example.bookhub.main.controller;

import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.service.NewBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class NewBookController {


    private final NewBookService newBookService;

    @GetMapping("/main/newbook")
    public String newBook(
            @RequestParam(name="cate", required=false, defaultValue= "1") int cate,
            @RequestParam(name="cateKeyword", required=false, defaultValue= "0") int cateKeyword,
            @RequestParam(name="pubDate", required=false, defaultValue= "1") int pubDate,
            @RequestParam(name="sort", required=false, defaultValue= "1") int sort,
            @RequestParam(name="page", required=false, defaultValue= "1") int page,

            Model model) { // Model 객체를 메서드 파라미터로 추가
        SearchCriteria searchCriteria = new SearchCriteria(); // 검색 조건 생성
        searchCriteria.setCate(cate);
        searchCriteria.setCateKeyword(cateKeyword);
        searchCriteria.setPubDate(pubDate);
        searchCriteria.setSort(sort);
        searchCriteria.setPage(page)
        ;
        BookListDto dto = newBookService.newBooks(searchCriteria); // NewBookService의 인스턴스를 통해 newBooks 메서드 호출
        model.addAttribute("book", dto.getBooks()); // 모델에 책 정보를 담아서 HTML로 전달
        model.addAttribute("criteria", dto.getCriteria());
        return "main/newList.html";
    }
    }
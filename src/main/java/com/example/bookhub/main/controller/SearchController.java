package com.example.bookhub.main.controller;


import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class SearchController {
    private final SearchService searchService;


    //  출시일 순 검색 했을때
    @GetMapping("/search")
    public String searchBooks(SearchCriteria searchCriteria, Model model) {;
        BookListDto dto = searchService.searchBooks(searchCriteria);
        model.addAttribute("book", dto.getBooks()); // 모델에 책 정보를 담아서 HTML로 전달.
        model.addAttribute("criteria", dto.getCriteria());
        return "main/searchList"; // searchList.html로 반환
    }

}



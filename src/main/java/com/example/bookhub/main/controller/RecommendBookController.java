package com.example.bookhub.main.controller;

import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.service.RecommendBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RecommendBookController {
    private final RecommendBookService recommendBookService;

    @GetMapping("/main/recobook")
    public String newBook(SearchCriteria searchCriteria, Model model) { // Model 객체를 메서드 파라미터로 추가
        BookListDto dto = recommendBookService.recoBooks(searchCriteria); // recommendBookService의 인스턴스를 통해 recoBooks 메서드 호출
        model.addAttribute("book", dto.getBooks()); // 모델에 책 정보를 담아서 HTML로 전달
        model.addAttribute("criteria", dto.getCriteria());
        return "main/recoList.html";
    }
}

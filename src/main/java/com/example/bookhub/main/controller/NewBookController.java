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
    public String newBook(SearchCriteria searchCriteria, Model model) { // Model 객체를 메서드 파라미터로 추가
        BookListDto dto = newBookService.newBooks(searchCriteria); // NewBookService의 인스턴스를 통해 newBooks 메서드 호출
        model.addAttribute("book", dto.getBooks()); // 모델에 책 정보를 담아서 HTML로 전달
        model.addAttribute("criteria", dto.getCriteria());
        return "main/newList.html";
    }
    }
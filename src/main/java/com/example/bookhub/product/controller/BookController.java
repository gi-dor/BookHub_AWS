package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.ReviewListDto;
import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/product/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private String userId;

    @GetMapping("/detail")
    public String home(@RequestParam("bookNo") long bookNo, Model model, Principal principal,
                       @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue="date") String sort){
        BookDto book = bookService.getBookDetailByNo(bookNo);
        if(principal != null) {
            userId = principal.getName();
        } else {
            // 임의의 사용자 이름으로 리뷰를 가져옴
            userId = "guest";
        }
        ReviewListDto reviewListDto = reviewService.getReviewsByBookNo(bookNo, userId, page, sort);
        model.addAttribute("book", book);
        model.addAttribute("reviewDtoList", reviewListDto.getReviewDtoList());
        model.addAttribute("page", reviewListDto.getPagination());

        return "product/book/detail";
    }
}

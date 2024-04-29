package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.ReviewDto;
import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/product/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/detail")
    public String home(@RequestParam("bookNo") long bookNo, Model model, Principal principal){
        BookDto book = bookService.getBookDetailByNo(bookNo);
        List<ReviewDto> reviewDtoList = reviewService.getReviewsByBookNo(bookNo, principal.getName());
        model.addAttribute("book", book);
        model.addAttribute("reviewDtoList", reviewDtoList);

        return "product/book/detail";
    }
}

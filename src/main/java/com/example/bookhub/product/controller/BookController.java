package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/detail")
    public String home(@RequestParam("bookNo") long bookNo, Model model){
        Book book = bookService.getBookDetailByNo(bookNo);
        model.addAttribute("book", book);

        return "product/book/detail";
    }
}

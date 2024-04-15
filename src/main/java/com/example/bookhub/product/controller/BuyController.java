package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BookService bookService;

    @PostMapping("/order")
    public String getOrder(
            @RequestParam("selectedBook") String[] selectedBookList,
            @RequestParam("selectedBookCount") int[] selectedBookCountList,
            Model model)
    {
        List<Book> orderBookList = new ArrayList<>();
        for (String selectedBook : selectedBookList) {
            long selectedBookNo = Long.valueOf(selectedBook);
            Book orderBook = bookService.getBookDetailByNo(selectedBookNo);
            orderBookList.add(orderBook);
        }
        model.addAttribute("orderBookList", orderBookList);

        List<Integer> orderBookCountList = new ArrayList<>();
        for (int selectedBookCount : selectedBookCountList) {
            orderBookCountList.add(selectedBookCount);
        }
        model.addAttribute("orderBookCountList", orderBookCountList);
        return "product/buy/order";
    }

}

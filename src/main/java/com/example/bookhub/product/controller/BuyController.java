package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.service.BuyService;
import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.CouponProduced;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BookService bookService;
    private final BuyService buyService;

    @PostMapping("/order")
    public String getOrder(
            @RequestParam("selectedBook") String[] selectedBookList,
            @RequestParam("selectedBookCount") int[] selectedBookCountList,
            Model model)
    {
        List<Book> orderBookList = new ArrayList<>();
        for (String selectedBook : selectedBookList) {
            long selectedCartNo = Long.valueOf(selectedBook);
            long selectedBookNo = buyService.getBookNoByCartNo(selectedCartNo);
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/coupon")
    @ResponseBody
    public ResponseEntity<List<CouponProduced>> getCoupon(Principal principal){
        List<CouponProduced> couponList = buyService.getCouponsByUserNo(principal.getName());
        System.out.println(couponList);
        return ResponseEntity.ok(couponList);
    }

}

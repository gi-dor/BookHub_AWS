package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.BookDto;
import com.example.bookhub.product.dto.ReviewListDto;
import com.example.bookhub.product.service.BookService;
import com.example.bookhub.product.service.BuyService;
import com.example.bookhub.product.service.ReviewService;
import com.example.bookhub.product.service.WishListService;
import com.example.bookhub.product.vo.BookAuthor;
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
    private final WishListService wishListService;
    private final BuyService buyService;
    private String userId;

    @GetMapping("/detail")
    public String home(@RequestParam("bookNo") long bookNo, Model model, Principal principal,
                       @RequestParam(required = false, defaultValue = "1") int page,
                       @RequestParam(required = false, defaultValue="date") String sort,
                       @RequestParam(required = false, defaultValue="total") String option){
        BookDto book = bookService.getBookDetailByNo(bookNo);
        if(principal != null) {
            userId = principal.getName();
        } else {
            // 임의의 사용자 이름으로 리뷰를 가져옴
            userId = "guest";
        }

        List<BookAuthor> bookAuthorList = bookService.getAuthorByBookNo(bookNo);

        String wishListYn = wishListService.getWishListYn(bookNo, userId);
        ReviewListDto reviewListDto = reviewService.getReviewsByBookNo(bookNo, userId, page, sort, option);
        String buyerYn = buyService.getBuyerYn(bookNo, userId);

        model.addAttribute("book", book);
        model.addAttribute("bookAuthorList", bookAuthorList);
        model.addAttribute("wishListYn", wishListYn);
        model.addAttribute("reviewDtoList", reviewListDto.getReviewDtoList());
        model.addAttribute("page", reviewListDto.getPagination());
        model.addAttribute("buyerYn", buyerYn);
        model.addAttribute("option", option);
        model.addAttribute("sort", sort);

        return "product/book/detail";
    }
}

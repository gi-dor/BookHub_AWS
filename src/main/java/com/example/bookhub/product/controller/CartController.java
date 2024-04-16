package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.CartService;
import com.example.bookhub.product.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/product/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/list")
    public String cart(Model model){
       List<Book> cartBooks = cartService.findCartList(1);
       model.addAttribute("cartBooks", cartBooks);

       return "product/cart/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("cartNo") long cartNo){
        cartService.deleteBookByCartNo(cartNo);
        return "redirect:/product/cart/list";
    }

    @GetMapping("/update/count")
    @ResponseBody
    public ResponseEntity<Void> updateCount(@RequestParam("cartNo") long cartNo, @RequestParam("count") int count){
        cartService.updateBookCountByCartNo(cartNo, count);
        return ResponseEntity.ok().build();
    }
}

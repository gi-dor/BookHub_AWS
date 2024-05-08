package com.example.bookhub.product.controller;

import com.example.bookhub.product.dto.CartBookDto;
import com.example.bookhub.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/product/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String cart(Principal principal, Model model){
       List<CartBookDto> cartBooks = cartService.findCartList(principal.getName());
       model.addAttribute("cartBooks", cartBooks);

       return "product/cart/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    public String addCart(@RequestParam("no") List<Long> bookNoList, Principal principal) {

        cartService.addCart(bookNoList, principal.getName());

        return "redirect:/product/cart";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/{bookNo}")
    @ResponseBody
    public ResponseEntity<String> createCart(@PathVariable("bookNo") long bookNo, Principal principal){
        String existOrNot = cartService.createCart(bookNo, principal.getName());
        return ResponseEntity.ok().body("{\"existOrNot\": \"" + existOrNot + "\"}");
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("cartNo") long cartNo){
        cartService.deleteBookByCartNo(cartNo);
        return "redirect:/product/cart";
    }

    @GetMapping("/update/count")
    @ResponseBody
    public ResponseEntity<Void> updateCount(@RequestParam("cartNo") long cartNo, @RequestParam("count") int count){
        cartService.updateBookCountByCartNo(cartNo, count);
        return ResponseEntity.ok().build();
    }
}

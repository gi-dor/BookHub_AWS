package com.example.bookhub.product.controller;

import com.example.bookhub.product.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/product/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/{bookNo}")
    @ResponseBody
    public ResponseEntity<Void> createWishList(@PathVariable("bookNo") long bookNo, Principal principal){
        wishListService.createWishList(bookNo, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{bookNo}")
    @ResponseBody
    public ResponseEntity<Void> deleteWishList(@PathVariable("bookNo") long bookNo, Principal principal){
        wishListService.deleteWishList(bookNo, principal.getName());
        return ResponseEntity.ok().build();
    }
}

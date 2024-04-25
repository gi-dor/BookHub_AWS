package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "opt", required = false) String opt,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                       @RequestParam(name = "sort", required = false, defaultValue = "productName") String sort,
                       Model model) {

        Pagination paging = productService.getPagination(opt, keyword, page, rows);
        List<BookList> books = productService.getBooks(opt, keyword, paging.getBegin(), rows, sort);

        model.addAttribute("paging", paging);
        model.addAttribute("books", books);

        return "admin/product/list";
    }
}

package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.dto.ProductFilter;
import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.service.ProductService;
import com.example.bookhub.admin.vo.Category;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                       @RequestParam(name = "sort", required = false, defaultValue = "productName") String sort,
                       @ModelAttribute("productFilter") ProductFilter filter,
                       Model model) {

        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Publisher> publishers = productService.getPublishers();

        int totalRows = productService.getTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);

        int begin = pagination.getBegin();
        List<BookList> books = productService.getBooks(filter, begin, rows, sort);

        model.addAttribute("topLevelCategories", topLevelCategories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("paging", pagination);
        model.addAttribute("books", books);
        model.addAttribute("productFilter", new ProductFilter());

        return "admin/product/list";
    }

}

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

    private static final int START_OFFSET = 1;

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                       @RequestParam(name = "sort", required = false, defaultValue = "productName") String sort,
                       @ModelAttribute("productFilter") ProductFilter filter,
                       Model model) {

        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Category> secondLeveCategories = categoryService.getSubCategoriesByCategoryNo(filter.getTopCategoryNo());
        List<Category> thirdLeveCategories = categoryService.getSubCategoriesByCategoryNo(filter.getSecondCategoryNo());
        List<Publisher> publishers = productService.getPublishers();

        int totalRows = productService.getTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);
        int begin = pagination.getBegin() - START_OFFSET;
        List<BookList> books = productService.getBooks(filter, begin, rows, sort);

        // 모든 topCategory 화면에 전달
        model.addAttribute("topLevelCategories", topLevelCategories);

        // topCategory가 선택된 경우 관련된 secondCategory 화면에 전달
        if (filter.getTopCategoryNo() != 0) {
            model.addAttribute("secondLevelCategories", secondLeveCategories);
        }

        // secondCategory가 선택된 경우 관련된 thirdCategory 화면에 전달
        if (filter.getSecondCategoryNo() != 0) {
            model.addAttribute("thirdLevelCategories", thirdLeveCategories);
        }

        model.addAttribute("publishers", publishers);
        model.addAttribute("paging", pagination);
        model.addAttribute("books", books);
        model.addAttribute("productFilter", filter);

        return "admin/product/list";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("no") Long productNo, Model model) {
        return "admin/product/modify";
    }

}

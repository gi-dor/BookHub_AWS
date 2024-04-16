package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.vo.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Category> secondLevelCategories = categoryService.getAllSecondLevelCategories();
        List<Category> thirdLevelCategories = categoryService.getAllThirdLevelCategories();

        model.addAttribute("topLevelCategories", topLevelCategories);
        model.addAttribute("secondLevelCategories", secondLevelCategories);
        model.addAttribute("thirdLevelCategories", thirdLevelCategories);

        return "admin/category";
    }

    @GetMapping("/secondCategory")
    @ResponseBody
    public List<Category> secondCategory(@RequestParam("category") int categoryNo) {
        return categoryService.getSecondLevelCategoriesByTopLevelCategoryNo(categoryNo);
    }

    @GetMapping("/thirdCategory")
    @ResponseBody
    public List<Category> thirdCategory(@RequestParam("category") int categoryNo) {
        return categoryService.getThirdLevelCategoriesBySecondLevelCategoryNo(categoryNo);
    }
}

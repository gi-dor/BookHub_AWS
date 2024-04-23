package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.vo.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        return "admin/items/category";
    }

    @GetMapping("/getSubCategories")
    @ResponseBody
    public List<Category> getSubCategories(@RequestParam("categoryNo") int categoryNo) {
        return categoryService.getSubCategoriesByCategoryNo(categoryNo);
    }

    @GetMapping("/getParentCategory")
    @ResponseBody
    public Category parentCategory(@RequestParam("categoryNo") int categoryNo) {
        return categoryService.getParentCategoryByCategoryNo(categoryNo);
    }

    @GetMapping("/getSecondCategories")
    @ResponseBody
    public List<Category> getSecondCategories() {
        return categoryService.getAllSecondLevelCategories();
    }

    @PostMapping("/addTopCategory")
    @ResponseBody
    public Category addTopLevelCategory(@RequestParam("categoryName") String categoryName) {
        categoryService.addTopLevelCategory(categoryName);
        return categoryService.getTopLevelCategoryByCategoryName(categoryName);
    }

    @PostMapping("/addSubCategory")
    @ResponseBody
    public Category addSubCategory(@RequestParam("categoryName") String categoryName,
                                   @RequestParam("CategoryNo") int categoryNo) {
        categoryService.addSubCategory(categoryName, categoryNo);
        return categoryService.getSubLevelCategoryByCategoryNameAndSuperCategoryNo(categoryName, categoryNo);
    }

    @PostMapping("/modify/thirdCategory")
    @ResponseBody
    public void modifyThirdCategory(@RequestParam("targetCategoryNo") int targetCategoryNo,
                                    @RequestParam("parentCategoryNo") int parentCategoryNo,
                                    @RequestParam("thirdCategoryName") String thirdCategoryName) {
        categoryService.modifyThirdCategory(targetCategoryNo, parentCategoryNo, thirdCategoryName);
    }

    @PostMapping("/modify/secondCategory")
    @ResponseBody
    public void modifySecondCategory(@RequestParam("targetCategoryNo") int targetCategoryNo,
                                     @RequestParam("parentCategoryNo") int parentCategoryNo,
                                     @RequestParam("secondCategoryName") String secondCategoryName) {
        categoryService.modifySecondCategory(targetCategoryNo, parentCategoryNo, secondCategoryName);
    }

    @PostMapping("/modify/topCategory")
    @ResponseBody
    public List<Category> modifyTopCategory(@RequestParam("targetCategoryNo") int targetCategoryNo,
                                            @RequestParam("topCategoryName") String topCategoryName) {
        categoryService.modifyTopCategory(targetCategoryNo, topCategoryName);
        return categoryService.getAllTopLevelCategories();
    }

    @PostMapping("/delete/thirdCategory")
    @ResponseBody
    public void deleteThirdCategory(@RequestParam("targetCategoryNo") int targetCategoryNo) {
        categoryService.deleteThirdCategory(targetCategoryNo);
    }

    @PostMapping("/delete/secondCategory")
    @ResponseBody
    public void deleteSecondCategory(@RequestParam("targetCategoryNo") int targetCategoryNo) {
        categoryService.deleteSecondCategory(targetCategoryNo);
    }

    @PostMapping("/delete/topCategory")
    @ResponseBody
    public List<Category> deleteTopCategory(@RequestParam("targetCategoryNo") int targetCategoryNo) {
        categoryService.deleteTopCategory(targetCategoryNo);
        return categoryService.getAllTopLevelCategories();
    }

    @GetMapping("/getTotalSubCategories")
    @ResponseBody
    public List<Category> getTotalSubCategories(@RequestParam("categoryNo") int categoryNo) {
        return categoryService.getTotalSubCategories(categoryNo);
    }

}

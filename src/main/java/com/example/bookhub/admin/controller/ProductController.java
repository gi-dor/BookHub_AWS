package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.dto.Product;
import com.example.bookhub.admin.dto.ProductFilter;
import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.service.ProductService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.admin.vo.Category;
import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Publisher;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                       @ModelAttribute("filter") ProductFilter filter, HttpSession session,
                       Model model) {

        // 비로그인 접근 차단
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Category> secondLeveCategories = categoryService.getSubCategoriesByCategoryNo(filter.getTopCategoryNo());
        List<Category> thirdLeveCategories = categoryService.getSubCategoriesByCategoryNo(filter.getSecondCategoryNo());
        List<Publisher> publishers = productService.getPublishers();

        int totalRows = productService.getTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);

        if (totalRows > 0) {
            int begin = pagination.getBegin() - START_OFFSET;
            List<BookList> books = productService.getBooks(filter, begin, rows, sort);
            model.addAttribute("books", books);
        } else {
            model.addAttribute("books", List.of());
        }
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
        model.addAttribute("filter", filter);

        return "admin/product/list";
    }

    @PostMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody List<Long> deletedProductNos) {
        productService.deleteProductByNo(deletedProductNos);
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("no") Long productNo, HttpSession session, Model model) {
        // 비로그인 접근 차단
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        Product product = productService.getProductByNo(productNo);
        Long secondCategoryNo = product.getSecondCategoryNo();
        Long topLevelCategoryNo = productService.getSuperCategoryNoBySubCategoryNo(secondCategoryNo);
        product.setTopCategoryNo(topLevelCategoryNo);

        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Category> secondLeveCategories = categoryService.getSubCategoriesByCategoryNo(topLevelCategoryNo);
        List<Publisher> publishers = productService.getPublishers();

        model.addAttribute("product", product);
        model.addAttribute("topLevelCategories", topLevelCategories);
        if (topLevelCategoryNo != 0) {
            model.addAttribute("secondLevelCategories", secondLeveCategories);
        }
        model.addAttribute("publishers", publishers);

        return "admin/product/modify";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute("product") Product modifiedProduct) {
        productService.modifyProduct(modifiedProduct);

        return "redirect:/admin/product/list";
    }

    @GetMapping("/create")
    public String create(HttpSession session, Model model) {
        // 비로그인 접근 차단
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        
        List<Category> topLevelCategories = categoryService.getAllTopLevelCategories();
        List<Publisher> publishers = productService.getPublishers();
        List<Author> authors = productService.getAuthors();

        model.addAttribute("topLevelCategories", topLevelCategories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        model.addAttribute("product", new Product());

        return "admin/product/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product createdProduct) {
        productService.createProduct(createdProduct);

        return "redirect:/admin/product/list";
    }

}

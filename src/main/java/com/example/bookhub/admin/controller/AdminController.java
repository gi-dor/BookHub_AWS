package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.service.AdminService;
import com.example.bookhub.admin.service.CategoryService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.admin.vo.Category;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final CategoryService categoryService;

    @GetMapping("/home")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(String id, String password, HttpSession session) {

        Admin admin = adminService.login(id, password);

        if (admin != null) {
            session.setAttribute("admin", admin);
        } else {
            return "redirect:/admin/login?error";
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/completed")
    public String complete() {
        return "admin/home";
    }

    // 회원가입 메소드
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("adminRegisterForm", new AdminRegisterForm());
        return "admin/login";
    }

    @PostMapping("/signup")
    public String signup(AdminRegisterForm form, BindingResult error, RedirectAttributes redirect) {
        if (error.hasErrors()) {
            return "admin/login";
        }
        try {
            // 유효성 체크를 통과한 경우
            Admin admin = adminService.join(form);
            redirect.addFlashAttribute("admin", admin);
            return "redirect:/home";
        } catch (Exception ex) {
            return "admin/login";
        }
    }

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

}

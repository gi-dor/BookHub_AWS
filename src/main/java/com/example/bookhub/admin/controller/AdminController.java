package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.exception.AlreadyAdminEmailException;
import com.example.bookhub.admin.exception.AlreadyAdminIdException;
import com.example.bookhub.admin.service.AdminService;
import com.example.bookhub.admin.vo.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/home")
    public String home(){
        return "admin/dash/totaluser";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(String id, String password, HttpSession session){

        Admin admin = adminService.login(id, password);

        if(admin != null){
            session.setAttribute("admin", admin);
        }  else {
            return "redirect:/admin/login?error";
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/completed")
    public String complete(){
        return "admin/home";
    }

    // 회원가입 메소드
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("adminRegisterForm", new AdminRegisterForm());
        return "admin/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid AdminRegisterForm form, BindingResult error, RedirectAttributes redirect){
        // 폼 입력값 유효성 체크를 통과하지 못한 경우
        if(error.hasErrors()){
            // 회원가입 실패시 입력 데이터 유지
            return "admin/signup";
        }
        try{
            // 유효성 체크를 통과한 경우
            Admin admin = adminService.join(form);
            redirect.addFlashAttribute("admin", admin);
            return "redirect:/admin/home";

        } catch (AlreadyAdminIdException ex){
            error.rejectValue("id", null, ex.getMessage());
            return "admin/signup";
        } catch (AlreadyAdminEmailException ex){
            error.rejectValue("email", null, ex.getMessage());
            return "admin/signup";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        // 세션을 파기하는 역할을 수행
        session.invalidate();
        return "admin/login";
    }


}

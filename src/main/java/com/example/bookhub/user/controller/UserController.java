package com.example.bookhub.user.controller;


import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {

        return "user/loginForm";
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userSignupForm", new UserSignupForm());
        return "user/registerForm";
    }


    @PostMapping("/join")
    public String userJoin(@ModelAttribute("userSignupForm")
                           @Valid UserSignupForm form ,
                           BindingResult errors ) {
        // BindingResult객체에 오류가 있다면 , 유효성 체크를 통과하지 못한 것임으로 ,
        // 회원가입 폼으로 내부 이동 시킨다.
        if (errors.hasErrors()) {
            return "user/registerForm";
        }

        try {
            User user = userService.registerUser(form);
            return "redirect:/user/completed?id=" + user.getId();

        } catch (RuntimeException ex) {
            String message = ex.getMessage();

            if("id".equals(message)) {
                errors.rejectValue("id", null,"사용 할 수 없는 아이디");
            } else if( "email".equals(message)) {
                errors.rejectValue("email", null, "사용 할 수 없는 이메일");
            }

            return "user/registerForm";
        }

    }


    @GetMapping("/completed")
    public String completed(String id , Model model) {
        User user = userService.selectUserById(id);
        model.addAttribute("user",user);

        return "user/completed";
    }


}

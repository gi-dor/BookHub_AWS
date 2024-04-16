package com.example.bookhub.user.controller;


import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(Model model , Principal principal) {

        if (principal == null || principal.getName() == null) {
            return "redirect:/user/loginForm ";
        } else {
            model.addAttribute("id",principal.getName());
            return "redirect:/user/myPage";
        }
    }

    // 마이페이지 - 회원정보 조회
    @GetMapping("/mypage/userInfo")
    public String userInfo(Model model , @RequestParam(name = "id") String userId) {
        User user = userService.selectUserById(userId);
        model.addAttribute("user" , user);
        return "user/userInfo";
    }

    /*
    // 마이페이지 - 회원정보 조회
    @GetMapping("/mypage/userInfo")
    public String userInfo2(Model model , Principal principal) {
        User user = userService.selectUserById(principal.getName());
        model.addAttribute("user" , user);
        return "user/userInfo";
    }
    */






    // 로그인 창으로 이동
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
        // 유효성 검사에서 오류가 발생했는지 확인하고, 오류가 있다면 사용자 등록 폼을 다시 보여주는 "user/registerForm" 뷰로 이동
        if (errors.hasErrors()) {
            return "user/registerForm";
        }

        try {
        //  UserService를 사용하여 사용자를 등록하고, 결과로 생성된 사용자 객체를 받는다
            User user = userService.registerUser(form);
        //  사용자 등록이 성공했을 경우, 사용자의 ID를 포함한 URL로 리다이렉트
            return "redirect:/user/completed?id=" + user.getId();

        } catch (RuntimeException ex) {
            System.out.println(ex);
            // 예외를 로깅하기
            logger.error("에러 발생: ", ex); // 에러 레벨
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



    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id) {
        int cnt = userService.idCheck(id);
        return cnt;
    }


    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email) {
        int cnt = userService.emailCheck(email);
        return  cnt;
    }

}

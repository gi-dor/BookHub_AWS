package com.example.bookhub.user.controller;


import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.exception.UserJoinException;
import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.util.MailService;
import com.example.bookhub.user.util.RandomPassword;
import com.example.bookhub.user.vo.User;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final MailService mailService;
    private final MyPageService myPageService;


     // 로그아웃
/*
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout123")
    public String logout( Principal principal) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if (user != null) {
            // 로그아웃 처리
           SecurityContextHolder.getContext().setAuthentication(null);
//               SecurityContextHolder.clearContext(); // 인증 객체를 모두 제거
        }
        return "redirect:/";
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
            // 동기
             User user = userService.registerUser(form);

            // 비동기
            //User user = userService.registerUserAsync(form);

            //  사용자 등록이 성공했을 경우, 사용자의 ID를 포함한 URL로 리다이렉트
            return "redirect:/user/completed?id=" + user.getId();

        } catch (UserJoinException ex) {
            // 예외를 로깅 , 출력 하기
            System.out.println(ex);
            logger.error("에러 발생: ", ex); // 에러 레벨
            String message = ex.getMessage();

            errors.rejectValue("id", null, ex.getMessage());
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

    // 비밀번호 재설정 페이지로 이동하는 핸들러
    @GetMapping("/login/passwordReset")
    public String showPasswordResetPage() {
        return "user/passwordResetForm"; // 비밀번호 재설정 폼으로 이동
    }


    // 비밀번호 재설정 요청 처리 핸들러
    @PostMapping("/login/passwordReset")
    public String resetPassword(@RequestParam("id") String id,
                                @RequestParam("email1") String email1 ,
                                @RequestParam("email2") String email2 ,
                                Model model) {

        try{
            // 입력한 id,email 로 사용자 조회하기
            String email = email1 + "@" + email2;
            User user = userService.selectUserByIdAndEmail(id,email);

            // 임시비밀번호 만들기 +  DB에 update
            String resetPassword = userService.resetPassword(id,email);
            // 임시 비밀번호값 resetPassword
            model.addAttribute("resetPassword",resetPassword);

            // 사용자 조회한 값으로 eamil 보내기
            String to = user.getEmail();
            String subject = "BookHub 임시비밀번호 발급.";
            String html = mailService.resetPasswordTemplate(resetPassword); // loadHtmlTemplate 메서드를 호출할 때는 괄호를 포함하여 호출해야 합니다.
            mailService.sendEmail(to, subject, html);


            return "/user/loginForm";
        } catch (IllegalArgumentException aex) {
            // 사용자 정보가 일치하지 않거나 누락된 경우 에러 메시지를 모델에 추가
            model.addAttribute("error",aex.getMessage());
            return "/user/passwordResetForm";
        }
        catch (Exception ex) {
            return "/main";
        }

    }

}
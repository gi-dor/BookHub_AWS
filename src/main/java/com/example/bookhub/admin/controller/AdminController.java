package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.dto.MailSend;
import com.example.bookhub.admin.dto.ReviewDto;
import com.example.bookhub.admin.dto.RatioDto;
import com.example.bookhub.admin.exception.AlreadyAdminEmailException;
import com.example.bookhub.admin.exception.AlreadyAdminIdException;
import com.example.bookhub.admin.service.AdminService;
import com.example.bookhub.admin.service.DashBoardService;
import com.example.bookhub.admin.vo.Admin;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final DashBoardService dashBoardService;
    private final MailSend mailSend;

    @GetMapping("/home/about")
    public String cover(){
        return "admin/home";
    }


    @GetMapping("/home")
    public String home(Model model){
        int noAnswerCnt = dashBoardService.noAnswerCnt();
        int answerCnt = dashBoardService.answerCnt();
        List<ReviewDto> dto = dashBoardService.getReviews();
        float avgRate = dashBoardService.averageRate();
        int noAnswerRatio = dashBoardService.noAnswerRatio();
        int answerRatio = dashBoardService.answerRatio();
        List<RatioDto> ratio = dashBoardService.getRatios();

        model.addAttribute("noAnswer", noAnswerCnt);
        model.addAttribute("answer", answerCnt);
        model.addAttribute("reviews", dto);
        model.addAttribute("avgRate", avgRate);
        model.addAttribute("noAnswerRatio", noAnswerRatio);
        model.addAttribute("answerRatio", answerRatio);
        model.addAttribute("ratio", ratio);
        return "admin/dash/totaluser";
    }

    @GetMapping("/login")
    public String loginForm(){
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
            long start = System.currentTimeMillis();
            // 회원가입시 입력한 이메일을 통해서 이메일을 전송한다
            String to = form.getEmail();
            mailSend.sendEmailAsync(to);

            Admin admin = adminService.join(form);

            long end = System.currentTimeMillis();

            redirect.addFlashAttribute("admin", admin);
            long finishTime = end - start;
            log.info(":::    비동기 이메일 작업방식 총 작업 소요 시간 " + finishTime + "ms");
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
        return "main";
    }


}

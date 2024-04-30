package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.admin.exception.NoAdminLoginException;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.service.AdminService;
import com.example.bookhub.admin.service.IndividualService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualService;
    private final AdminService adminService;
    private final AdminMapper adminMapper;

    // 미완료 CS
    @GetMapping("/admin/individual/noanswer")
    public String noAnswerList(Model model, IndividualDto dto){
        List<IndividualDto> saveDto = individualService.getNoAnswerList();
        model.addAttribute("noanswer", saveDto);
        return "admin/individual/noanswerlist";
    }

    // 미완료 디테일
    @GetMapping("/admin/individual/noanswer/detail/{no}")
    public String noAnswerDetail(@PathVariable Long no, Model model){
        Inquiry inquiry = individualService.getNoAnswerNo(no);
        model.addAttribute("inquiry", inquiry);
        return "admin/individual/noanswerdetail";
    }

    @PostMapping("/admin/individual/noanswer/add")
    public String answer(@RequestParam Long no, HttpSession session, String content, RedirectAttributes redirectAttributes){
        // Admin 로그인이 되어 있는지 체크
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null){
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 다시 진행하세요");
            return "redirect:/admin/individual/noanswer/detail/" + no;
        }

        try{
            individualService.insertAnswer(no, content, admin);
            // 성공 메시지 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("successMessage", "답변이 성공적으로 등록되었습니다.");
            return "redirect:/admin/individual/noanswer";
        } catch (NoAdminLoginException ex){
            // 예외 메시지를 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 다시 진행하세요");
            return "/admin/individual/noanswer/detail/" + no;
        }
    }
    // 미완료 CS 끝



    // 완료 CS
    @GetMapping("/admin/individual/answer")
    public String answerList(Model model, IndividualDto dto){
        List<IndividualDto> saveDto = individualService.getAnswerList();
        model.addAttribute("answer", saveDto);
        return "admin/individual/answerlist";
    }

    // 완료 디테일
    @GetMapping("/admin/individual/answer/detail/{no}")
    public String answerDetail(@PathVariable Long no, Model model){
        InquiryComment answerInquiry = individualService.getAnswerNo(no);
        Inquiry noAnswerInquiry = individualService.getNoAnswerNo(no);
        Admin admin = new Admin();
        admin.setNo(answerInquiry.getAdmin().getNo());
        Admin saveId = adminMapper.getAdminNo(admin.getNo());


        model.addAttribute("admin", saveId);
        model.addAttribute("answer", answerInquiry);
        model.addAttribute("noanswer", noAnswerInquiry);
        return "admin/individual/answerdetail";
    }

}

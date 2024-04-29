package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.service.AdminService;
import com.example.bookhub.admin.service.IndividualService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String answer(@RequestParam Long no,  HttpSession session, String content){
        Admin admin = (Admin) session.getAttribute("admin");
        individualService.insertAnswer(no, content, admin);
        return "redirect:/admin/individual/noanswer/detail/" + no;
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
        Admin saveId = adminMapper.getAdminnNo(admin.getNo());


        model.addAttribute("admin", saveId);
        model.addAttribute("answer", answerInquiry);
        model.addAttribute("noanswer", noAnswerInquiry);
        return "admin/individual/answerdetail";
    }

}

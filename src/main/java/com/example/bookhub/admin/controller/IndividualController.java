package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.IndividualDto;
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

    @GetMapping("/admin/individual/noanswer")
    public String noAnswerList(Model model, IndividualDto dto){
        List<IndividualDto> saveDto = individualService.getNoAnswerList();
        model.addAttribute("noanswer", saveDto);
        return "admin/individual/noanswerlist";
    }

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

}

package com.example.bookhub.board.controller;

import com.example.bookhub.admin.service.IndividualService;
import com.example.bookhub.board.dto.InquiryListDto;
import com.example.bookhub.board.service.FaqService;
import com.example.bookhub.board.service.InquiryService;
import com.example.bookhub.board.vo.FaqCategory;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final FaqService faqService;
    private final UserService userService;
    private final IndividualService individualService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String findAllInquiry(Model model, @RequestParam(defaultValue = "1") int page, Principal principal) {

        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        InquiryListDto dto = inquiryService.findAllInquiry(page, user.getNo());
        List<FaqCategory> faqCategory = faqService.findAllCategories();

        model.addAttribute("categories", faqCategory);
        model.addAttribute("inquiries", dto.getInquiries());
        model.addAttribute("page", dto.getPagination());

        return "board/inquiry/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public String registerForm(Model model) {

        List<FaqCategory> catList =faqService.findAllCategories();
        List<FaqCategory> faqCategory = faqService.findAllCategories();

        model.addAttribute("categories", faqCategory);
        model.addAttribute("catList", catList);

        return "board/inquiry/register";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("register")
    public String registerInquiry(String title, String content, Principal principal, @RequestParam(name = "catNo") long catNo) {
        String userId = principal.getName();

        inquiryService.insertInquiry(title, content, userId, catNo);

        return "redirect:/board/inquiry/list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{no}")
    public String getInquiryDetail(@PathVariable long no, Model model) {
        Inquiry inquiry = inquiryService.getInquiryByNo(no);
        List<InquiryComment> comments = inquiryService.getInquiryComment(no);

        List<FaqCategory> faqCategory = faqService.findAllCategories();

        model.addAttribute("categories", faqCategory);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("comments", comments);

        return "board/inquiry/detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{no}")
    public String modifyInquiryForm(@PathVariable long no, Model model) {
        Inquiry inquiry = inquiryService.getInquiryByNo(no);

        List<FaqCategory> faqCategories = faqService.findAllCategories();

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("faqCategory", faqCategories);

        return "board/inquiry/modify";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{no}")
    public String modifyInquiry(@ModelAttribute("inquiry") Inquiry inquiry, @RequestParam(name = "catNo") long catNo) {

        FaqCategory faqCategory = faqService.getFaqCategoryByNo(catNo);

        inquiry.setFaqCategory(faqCategory);

        inquiryService.modifyInquiry(inquiry);

        return "redirect:/board/inquiry/detail/{no}";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public String deleteInquiry(@RequestParam("no") long no) {
        inquiryService.deleteInquiry(no);

        return "redirect:/board/inquiry/list";
    }






}

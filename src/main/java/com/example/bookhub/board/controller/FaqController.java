package com.example.bookhub.board.controller;

import com.example.bookhub.board.dto.FaqListDto;
import com.example.bookhub.board.service.FaqService;
import com.example.bookhub.board.vo.Faq;
import com.example.bookhub.board.vo.FaqCategory;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/faq")
public class FaqController {

    private final FaqService faqService;


    @GetMapping("/list")
    public String findAllFaq(@RequestParam(name="cat", required = false, defaultValue = "0") int cat, @RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page, Model model) {
        FaqListDto dto = faqService.getFaqList(cat, keyword, page);

        List<FaqCategory> faqCategory = faqService.findAllCategories();

        model.addAttribute("categories", faqCategory);
        model.addAttribute("faqs", dto.getFaqs());
        model.addAttribute("page", dto.getPagination());

        return "board/faq/list";
    }

    @GetMapping("/detail/{no}")
    public String getFaqDetail(@PathVariable Long no, Model model) {
        Faq faq = faqService.getFaqByNo(no);
        model.addAttribute("faq", faq);
        return "board/faq/detail";

    }

}

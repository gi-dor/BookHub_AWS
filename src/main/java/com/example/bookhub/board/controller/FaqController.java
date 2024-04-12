package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.FaqService;
import com.example.bookhub.board.vo.Faq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/faq")
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/list")
    public String findAllFaq(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        List<Faq> faqs = faqService.findAllFaq(page, size);

        int totalFaqsCount = faqService.getTotalFaqCount();

        int totalPages = (int) Math.ceil((double) totalFaqsCount / size);

        model.addAttribute("faqs", faqs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "board/faq/list";
    }
}

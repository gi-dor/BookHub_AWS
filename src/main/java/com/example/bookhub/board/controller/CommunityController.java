package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.CommunityService;
import com.example.bookhub.board.vo.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/community")
public class CommunityController {


    private final CommunityService communityService;

    @GetMapping("/list")
    public String findAllCommunity(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        List<Community> communities = communityService.findAllCommunity(page, size);

        int totalNoticesCount = communityService.getTotalCommunitiesCount();

        int totalPages = (int) Math.ceil((double) totalNoticesCount / size);

        model.addAttribute("communities", communities);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "board/community/list";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "board/community/register";
    }

    @PostMapping("/register")
    public String registerCommunity(String title, String content) {
        communityService.insertNotice(title, content);

        return "redirect:/board/community/list";
    }

}

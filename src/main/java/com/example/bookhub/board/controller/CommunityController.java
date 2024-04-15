package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.CommunityService;
import com.example.bookhub.board.vo.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/detail/{no}")
    public String getCommunityDetail(@PathVariable Long no, Model model) {
        Community community = communityService.getCommunityByNo(no);
        model.addAttribute("community", community);
        return "board/community/detail";
    }

    @GetMapping("/modify/{no}")
    public String modifyForm(@PathVariable Long no, Model model) {
        Community community = communityService.getCommunityByNo(no);
        model.addAttribute("community", community);
        return "board/community/modify";
    }
    @PostMapping("/modify/{no}")
    public String updateCommunity(@ModelAttribute("community") Community community) {
        communityService.updateCommunity(community);
        /*
        나중에 게시글 디테일로 리다이렉트 변경
         */
        return "redirect:/board/community/detail/{no}";
    }

    @PostMapping("/delete")
    public String deleteCommunity(@RequestParam("no") Long no) {
        communityService.deleteCommunity(no);

        return "redirect:/board/community/list";
    }

    @PostMapping("/search")
    public String searchCommunity(@RequestParam("keyword") String keyword,
                                  @RequestParam("searchOption") String searchOption,
                                  Model model) {
        List<Community> searchResults = communityService.searchCommunity(keyword, searchOption);
        model.addAttribute("searchResults", searchResults);
        return "/board/community/list";
    }

}

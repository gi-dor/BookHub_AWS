package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.CommunityService;
import com.example.bookhub.board.vo.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String registerCommunity(String title, String content, Principal principal, @RequestParam("images") List<MultipartFile> images) {
        String userId = principal.getName();

        communityService.insertCommunity(title, content, userId, images);

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

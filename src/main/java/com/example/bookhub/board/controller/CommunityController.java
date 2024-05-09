package com.example.bookhub.board.controller;

import com.example.bookhub.board.dto.CommunityListDto;
import com.example.bookhub.board.service.CommentService;
import com.example.bookhub.board.service.CommunityService;
import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityComment;
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
    private final CommentService commentService;

    @GetMapping("/list")
    public String findAllCommunity(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String keyword) {

        CommunityListDto dto = communityService.findAllCommunity(keyword, page);

        model.addAttribute("communities", dto.getCommunities());
        model.addAttribute("page", dto.getPagination());

        return "board/community/list";
    }

    @PreAuthorize("isAuthenticated()")
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
    public String getCommunityDetail(@PathVariable Long no, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "parentNo", required = false) Long parentNo) {

        Community community = communityService.getCommunityByNo(no);
        communityService.viewCount(no);

        Long communityNo = community.getNo();

        List<CommunityComment> comments = commentService.findByCommunityNoComment(page, size, communityNo);


        int commentsCount = commentService.getCommentCount(communityNo);
        int totalPages = (int) Math.ceil((double) commentsCount / size);

        model.addAttribute("community", community);
        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "board/community/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{no}")
    public String modifyForm(@PathVariable Long no, Model model) {
        Community community = communityService.getCommunityByNo(no);
        model.addAttribute("community", community);
        return "board/community/modify";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{no}")
    public String updateCommunity(@ModelAttribute("community") Community community) {
        communityService.updateCommunity(community);

        return "redirect:/board/community/detail/{no}";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public String deleteCommunity(@RequestParam("no") Long no) {
        communityService.deleteCommunity(no);

        return "redirect:/board/community/list";
    }


}

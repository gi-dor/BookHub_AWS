package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.CommentService;
import com.example.bookhub.board.service.CommunityService;
import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityComment;
import com.example.bookhub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/community/comment")
public class CommentController {

    private final CommentService commentService;


//    @GetMapping("/list")
//    public String findByCommunityNoComment(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam Long communityNo) {
//
//        List<CommunityComment> comments = commentService.findByCommunityNoComment(page, size, communityNo);
//
//        int commentsCount = commentService.getCommentCount(communityNo);
//
//        int totalPages = (int) Math.ceil((double) commentsCount / size);
//    }


    @PostMapping("/add")
    public String addComment(@RequestParam Long communityNo, Principal principal, @RequestParam String comment) {
        String userId = principal.getName();

        commentService.addComment(communityNo, userId, comment);
        return "redirect:/board/community/detail/" + communityNo;
    }

}

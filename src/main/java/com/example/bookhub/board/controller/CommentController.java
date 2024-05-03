package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.CommentService;
import com.example.bookhub.board.vo.CommunityComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@Controller
@RequiredArgsConstructor
@RequestMapping("board/community/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    public String addComment(@RequestParam Long communityNo, Principal principal, @RequestParam String comment) {
        String userId = principal.getName();

        commentService.addComment(communityNo, userId, comment);
        return "redirect:/board/community/detail/" + communityNo;
    }

    @PostMapping("/addChild")
    public String addChildComment(@RequestParam("communityNo") long communityNo, @RequestParam("parentNo") long parentNo, Principal principal, @RequestParam("content") String content) {
        String userId = principal.getName();

        commentService.addChildComment(communityNo, parentNo, userId, content);

        return "redirect:/board/community/detail/" + communityNo;
    }

    @PostMapping("/modify")
    public String updateComment(@ModelAttribute("comment") CommunityComment comment, @RequestParam Long communityNo) {
        commentService.updateComment(comment);

        return "redirect:/board/community/detail/" + communityNo;
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam Long no, @RequestParam Long communityNo) {
        commentService.deleteComment(no);

        return "redirect:/board/community/detail/" + communityNo;
    }
}

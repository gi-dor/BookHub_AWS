package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.NoticeService;
import com.example.bookhub.board.vo.Notice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("board/notices")
public class NoticeController {


    private final NoticeService noticeService;

     public NoticeController (NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public String findAllNotice(Model model) {

        List<Notice> notices = noticeService.findAllNotice();
        model.addAttribute("notices", notices);

        return "board/notices/list";
    }


}

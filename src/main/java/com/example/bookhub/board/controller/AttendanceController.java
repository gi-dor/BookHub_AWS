package com.example.bookhub.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/attendance")
public class AttendanceController {

    @GetMapping("/")
    public String attendance() {

        return "/board/attendance/attendance";
    }
}

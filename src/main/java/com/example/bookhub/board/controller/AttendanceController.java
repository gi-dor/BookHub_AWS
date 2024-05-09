package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.AttendanceService;
import com.example.bookhub.board.vo.Attendance;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String attendance(Model model, Principal principal) {

        String id = principal.getName();
        User user = userService.selectUserById(id);

        // 춣석체크 테이블의 모든 정보 조회
        List<Attendance> attendances = attendanceService.userAttendanceCheck(user.getNo());


        model.addAttribute("attendances", attendances);
        model.addAttribute("user", user);


        return "board/attendance/attendance";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/attendanceCheck.do")
    @ResponseBody
    public  List<Attendance> attendanceCheck(Principal principal) {

        String id = principal.getName();
        User user = userService.selectUserById(id);

        return attendanceService.userAttendanceCheck(user.getNo());

    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/attendanceCheck")
    public String insertAttendance(@RequestParam("userNo") int userNo) {

        attendanceService.insertAttendance(userNo);

        if (attendanceService.attendanceCheckCount(userNo) == 10) {
            attendanceService.insertAttendanceCoupon(userNo);
        }

        return "redirect:/board/attendance/";
    }

}

package com.example.bookhub.board.controller;

import com.example.bookhub.board.service.AttendanceService;
import com.example.bookhub.board.vo.Attendance;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    @GetMapping("/")
    public String attendance(Model model, Principal principal) {

        String id = principal.getName();
        User user = userService.selectUserById(id);

        Attendance attendance = attendanceService.userAttendanceCheck(user.getNo());

        model.addAttribute("user", user);

        if (attendance != null) {
            model.addAttribute("attendance", attendance);
            boolean isUserAlreadyAttendance = user != attendance.getUser();
            model.addAttribute("isUserAlreadyAttendance", isUserAlreadyAttendance);

            LocalDate currentDate = LocalDate.now();
            LocalDate lastAttendanceDate = attendance.getCheckDate().toLocalDate();
            boolean isNextDay = !currentDate.isEqual(lastAttendanceDate);
            model.addAttribute("isNextDay", isNextDay);
        }

        return "board/attendance/attendance";
    }

    @PostMapping("/register")
    public String insertAttendance(@RequestParam("userNo") int userNo) {

        attendanceService.insertAttendance(userNo);

        return "redirect:/board/attendance/";
    }

    @PostMapping("/update")
    public String updateAttendance(@RequestParam("userNo") int userNo) {

        attendanceService.updateAttendance(userNo);

        return "redirect:/board/attendance/";
    }
}

package com.example.bookhub.user.index;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inquiry")
@RequiredArgsConstructor
public class IndexController {
    private final UserService userService;
    private final MyPageService myPageService;

    @GetMapping("/index")
    public String indexTest1(Principal principal) {

        String id = principal.getName();

        User user = userService.selectUserById(id);

        List<Inquiry> inquiryList = myPageService.getInquiryListById(user.getId());

        return  null;
    }


    @GetMapping("/dates")
    public Object findInquiryByDate(@RequestParam("startDate") String startDate ,
                                    @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate1 =  LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDate1 = LocalDateTime.parse(endDate, formatter);

        List<Inquiry> inquiryList = myPageService.findInquiryByDate(startDate1,endDate1);
        return inquiryList;
    }



}

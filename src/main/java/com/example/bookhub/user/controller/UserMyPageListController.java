package com.example.bookhub.user.controller;

import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.WishList;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/list")
public class UserMyPageListController {

    private final UserService userService;
    private final MyPageService myPageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/wishList")
    public String wishList(Model model , Principal principal) {

        // 로그인 ID 사용자 정보 조회
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 찜목록 조회
        List<WishList> wishList = myPageService.getWishListById(user.getId());

        model.addAttribute("wishList",wishList);

        return "/user/list/wishList";
    }


    @GetMapping("/orderList")
    public String orderListPage(Model model , Principal principal) {



        return "/user/list/orderList";
    }
}

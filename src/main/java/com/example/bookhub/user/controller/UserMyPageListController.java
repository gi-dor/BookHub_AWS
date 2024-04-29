package com.example.bookhub.user.controller;

import com.example.bookhub.user.dto.PageWishListDTO;
import com.example.bookhub.user.dto.WishListDTO;
import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserPagination;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/list")
public class UserMyPageListController {

    private final UserService userService;
    private final MyPageService myPageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/wishList")
    public String wishList(@RequestParam(name="page" , required = false , defaultValue="1") int page ,
                           Model model ,
                           Principal principal) {

        // 로그인 ID 사용자 정보 조회
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 찜목록 조회
        PageWishListDTO wishList = myPageService.getWishListById(user.getId() , page);
        int totalCnt = myPageService.getTotalWishListCount(user.getId());

        model.addAttribute("wishList",wishList.getWishListDTO());
        model.addAttribute("page" , wishList.getUserPagination());
        model.addAttribute("totalCnt" ,totalCnt);

        return "/user/list/wishList";
    }


    /*   하는중
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteWishList")
    public String deleteWishList(Principal principal , Long wishNo) {

        String id =  principal.getName();

        try{
            myPageService.deleteWishListByIdAndBookNo(wishNo,id );
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return "redirect:/mypage/list/wishList";
    }
    */


    @GetMapping("/orderList")
    public String orderListPage(Model model , Principal principal) {



        return "/user/list/orderList";
    }
}

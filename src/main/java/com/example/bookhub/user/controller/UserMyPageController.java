package com.example.bookhub.user.controller;

import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserMyPageController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;



    // 마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String myPage(Model model , Principal principal) {

        String userId = principal.getName();
        User user = userService.selectUserById(userId);
        model.addAttribute("user" , user);
        return "/user/mypage";
    }



    /**
     * 현재 로그인한 사용자의 회원 정보를 조회하는 컨트롤러 메서드입니다.
     *
     * @param model View에 전달할 데이터를 담는 Model 객체
     * @param principal 현재 로그인한 사용자의 Principal 객체
     * @return 회원 정보 페이지의 뷰 이름
     */
    // 마이페이지 - 회원정보 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userInfo")
    public String userInfo(Model model , Principal principal) {

        String userId = principal.getName();
        User user = userService.selectUserById(userId);
        model.addAttribute("user" , user);
        return "user/userInfo";

    }

   /*
     이렇게 쓰면 URL 주소창에 해당아이디가 보이며 , 로그인하지않아도 URL 조작만으로 회원정보 조회가된다
     그러므로 쓰지마

    @GetMapping("/mypage/userInfo")
    public String userInfo(Model model , @RequestParam(name = "id") String id) {
        User user = userService.selectUserById(id);
        model.addAttribute("user" , user);
        return "user/userInfo";
    }
    */





    /**
     * 회원 정보 수정 페이지를 제공하는 컨트롤러 메서드입니다.
     *
     * @param model View에 전달할 데이터를 담는 Model 객체
     * @param principal 현재 로그인한 사용자의 Principal 객체
     * @return 회원 정보 수정 페이지의 뷰 이름
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modifyUserInfoForm")
    public String modifyForm(Model model , Principal principal) {

        log.info("유저 아이디 log: " +principal.getName());
        System.out.println("유저 아이디  sop : " +principal.getName());

        // 현재 로그인한 사용자의 ID
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 회원 정보를 입력칸에 채워넣기
        model.addAttribute("user",user);

        return "user/modifyUserInfo";
    }


    /**
     * 사용자 정보를 수정하는 POST 요청을 처리하는 컨트롤러 메서드입니다.
     *
     * @param principal 현재 로그인한 사용자의 Principal 객체
     * @param user 수정된 사용자 정보를 포함하는 User 객체
     * @return 사용자 정보 수정 후 사용자 정보 페이지로 리다이렉트하는 URL 문자열
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modifyUserInfo")
    public String modifyUser(Principal principal , User user){

        String id = principal.getName();
        User userId = userService.selectUserById(id);

        userId.setTel(user.getTel());
        userId.setEmail(user.getEmail());
        userId.setZipCode(user.getZipCode());
        userId.setAddress(user.getAddress());
        userId.setAddressDetail(user.getAddressDetail());

        userService.modifyUserInfo(user);

        return  "redirect:/mypage/userInfo";
    }


}

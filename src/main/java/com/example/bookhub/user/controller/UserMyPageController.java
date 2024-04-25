package com.example.bookhub.user.controller;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.user.dto.ChangePasswordForm;
import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserMyPageController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final MyPageService myPageService;
    PasswordEncoder passwordEncoder;


    // 마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String myPage(Model model , Principal principal) {

        // 로그인 ID 사용자 정보 조회
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 쿠폰 갯수 카운팅
        int couponCnt = myPageService.countCoupon(userId);

        // 최근 주문목록 가져오기
        List<Buy> orderList =  myPageService.getOrderListById(userId);

        // 내가 작성한 1:1 문의 가져오기
        List<Inquiry> inquiryList = myPageService.getInquiryListById(userId);


        model.addAttribute("user" , user);
        model.addAttribute("couponCnt",couponCnt);
        model.addAttribute("orderList",orderList);
        model.addAttribute("inquiryList",inquiryList);

        return "/user/mypage/myPageMain";
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
        return "user/mypage/userInfo";

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

        return "user/mypage/modifyUserInfo";
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/deleteForm")
    public String deleteForm(Model model , Principal principal ) {

        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        model.addAttribute("user",user);

        return "user/mypage/deleteUserForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteUser")
    public String deleteUser(Principal principal , String password) {

        try{
            // 사용자 탈퇴 처리
            myPageService.deleteUserById(principal.getName(), password);
            // 로그아웃
            return "redirect:/user/logout";

        } catch (IllegalArgumentException ex) {
            return "redirect:/mypage/deleteForm?error"; // 비밀번호 오류 페이지로 리다이렉트
        }

    }

    /*
    // Authentication  객체를 사용해 현재 인증된 사용자 정보를 가져와서
    @PostMapping("/deleteAccount")
    public String deleteAccount(Authentication authentication) {
        // 현재 인증된 사용자의 정보 가져오기
        // UserPricipal 은 Spring Security에서 사용자의 정보를 담는 클래스
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // 사용자 탈퇴 처리
        userService.deleteUser(userPrincipal.getId());
        // 로그아웃
        return "redirect:/logout";
    }
    */


    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword(Principal principal,
                                 @Valid ChangePasswordForm form ,
                                 BindingResult errors) {

        // 입력 폼에 유효성 검사 에러가 있을 경우, "fail" 문자열을 반환
        if (errors.hasErrors()) {
           return "fail";
        }

        try {
            // 사용자의 아이디를 principal 객체로 획득
            String id = principal.getName();
            myPageService.updatePassword(id, form);
            return "success";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}

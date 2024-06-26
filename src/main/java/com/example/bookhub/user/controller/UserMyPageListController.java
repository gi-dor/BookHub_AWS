package com.example.bookhub.user.controller;

import com.example.bookhub.product.service.ReturnService;
import com.example.bookhub.product.vo.ReturnReason;
import com.example.bookhub.user.dto.InquiryListDTO;
import com.example.bookhub.user.dto.OrderDetailDTO;
import com.example.bookhub.user.dto.OrderListDTO;
import com.example.bookhub.user.dto.PageListDTO;
import com.example.bookhub.user.dto.WishListDTO;
import com.example.bookhub.user.service.MyPageService;
import com.example.bookhub.user.service.UserService;
import com.example.bookhub.user.vo.User;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/list")
public class UserMyPageListController {

    private final UserService userService;
    private final MyPageService myPageService;
    private final ReturnService returnService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/wishList")
    public String wishList(@RequestParam(name="page" , required = false , defaultValue="1") int page ,
                           Model model ,
                           Principal principal) {

        // 로그인 ID 사용자 정보 조회
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 찜목록 조회
        PageListDTO<WishListDTO> wishList = myPageService.getWishListById(user.getId() , page);

        model.addAttribute("wishList",wishList.getItems());
        model.addAttribute("page" , wishList.getUserPagination());

        return "user/list/wishList";
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/orderList")
    public String orderListPage(Model model , Principal principal,
                                @RequestParam(name="page" , required = false ,defaultValue="1") int page) {
        String id = principal.getName();
        User user = userService.selectUserById(id);

        // 구매자 정보로 주문내역 갯수 조회
        int totalRows = myPageService.countOrder(user.getId());

        // 구매자 주문내역 , 페이징 정보조회
        PageListDTO<OrderListDTO> orderList = myPageService.getOrderListByIdPage(user.getId() , page);

        model.addAttribute("totalRows" , totalRows);
        model.addAttribute("page" ,orderList.getUserPagination() );
        model.addAttribute("orderList" , orderList.getItems());

        return "user/list/orderList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/orderDetail/{no}")
    public String orderDetail(Model model ,
                              Principal principal,
                            @PathVariable("no") Long no ) {

        String id = principal.getName();
        User user = userService.selectUserById(id);

        //  구매자의 주문내역 상세페이지
        List<OrderDetailDTO> orderDetailDTO = myPageService.orderDetail(user.getId(),no);

        // 배송 상세
        List<OrderDetailDTO> deliveryDetail = myPageService.deliveryDetail(user.getId(),no);

        List<ReturnReason> returnReasonList = returnService.getReturnReasonList();
        System.out.println(returnReasonList);

        model.addAttribute("orderList",orderDetailDTO);
        model.addAttribute("delivery",deliveryDetail);
        model.addAttribute("returnReasonList", returnReasonList);

        System.out.println("=====================================================");
        System.out.println(":::::::::::"+orderDetailDTO);
        System.out.println("=====================================================");

        return "user/list/orderDetail";
    }


    // inquiryList?page=1
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/inquiryList")
    public String inquiryListPage(@RequestParam(name="page" , required = false ,defaultValue="1") int page,
                                  Model model,
                                  Principal principal) {

        // 로그인 ID 사용자 정보 조회
        String userId = principal.getName();
        User user = userService.selectUserById(userId);

        // 로그인한 사용자의 1:1 문의 목록 , 페이징 정보 조회
        PageListDTO<InquiryListDTO> inquiryList = myPageService.getInquiryListByIdPage(user.getId() , page);


        // 로그인한 사용자가 작성한 글의 갯수 조회
        int totalRows = myPageService.countInquiry(user.getId());

        model.addAttribute("totalRows",totalRows);
        model.addAttribute("inquiryList",inquiryList.getItems());
        model.addAttribute("page",inquiryList.getUserPagination());


        return "user/list/inquiryList";
    }

    @GetMapping("/inquiryListNoCache")
    public String inquiryListPageNoCache(@RequestParam(name="page" , required = false ,defaultValue="1") int page,
                                  Model model) {
        System.out.println(" :: 캐싱처리 안함      :: ");


        // 로그인한 사용자의 1:1 문의 목록 , 페이징 정보 조회
        PageListDTO<InquiryListDTO> inquiryList = myPageService.getNoCacheInquiriesList( page);


        // 로그인한 사용자가 작성한 글의 갯수 조회
        int totalRows = myPageService.countInquiriesAll();

        model.addAttribute("totalRows",totalRows);
        model.addAttribute("inquiryList",inquiryList.getItems());
        model.addAttribute("page",inquiryList.getUserPagination());


        return "user/list/inquiryList";
    }


    @GetMapping("/inquiryListCache")
    public String inquiryListPageCache(@RequestParam(name="page" , required = false ,defaultValue="1") int page,
                                       Model model) {
        System.out.println(" :: 캐싱처리 했음      :: ");


        // 로그인한 사용자의 1:1 문의 목록 , 페이징 정보 조회
        PageListDTO<InquiryListDTO> inquiryList = myPageService.getCacheInquiriesList( page);


        // 로그인한 사용자가 작성한 글의 갯수 조회
        int totalRows = myPageService.countInquiriesAll();

        model.addAttribute("totalRows",totalRows);
        model.addAttribute("inquiryList",inquiryList.getItems());
        model.addAttribute("page",inquiryList.getUserPagination());


        return "user/list/inquiryList";
    }
}

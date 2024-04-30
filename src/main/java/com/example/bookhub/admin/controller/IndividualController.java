package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.board.Pagination;
import com.example.bookhub.admin.exception.NoAdminLoginException;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.service.IndividualService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.board.vo.InquiryComment;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualService;
    private final AdminMapper adminMapper;

    // 미완료 CS
    @GetMapping("/admin/individual/noanswer")
    public String noAnswerList(@RequestParam(defaultValue = "1", value = "page") int page,
                                @RequestParam(defaultValue = "10", value = "rows") int rows,
                                Model model){
        // 전체 행 수를 구하는 메소드
        int totalRows = individualService.getTotalRows();
        Pagination pagination = new Pagination(page, totalRows, rows);

        // Pagination을 사용하여 페이지의 데이터 리스트를 가져옴
        List<IndividualDto> noAnswerList = individualService.getNoAnswerList(pagination);
        model.addAttribute("noAnswerList", noAnswerList);

        // 모델에 Pagination 추가
        model.addAttribute("pagination", pagination);
        return "admin/individual/noanswerlist";
    }

    // 미완료 디테일
    @GetMapping("/admin/individual/noanswer/detail/{no}")
    public String noAnswerDetail(@PathVariable Long no, Model model){
        Inquiry inquiry = individualService.getNoAnswerNo(no);
        model.addAttribute("inquiry", inquiry);
        return "admin/individual/noanswerdetail";
    }

    @PostMapping("/admin/individual/noanswer/add")
    public String answer(@RequestParam Long no, HttpSession session, String content, RedirectAttributes redirectAttributes){
        // Admin 로그인이 되어 있는지 체크
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null){
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 다시 진행하세요");
            return "redirect:/admin/individual/noanswer/detail/" + no;
        }

        try{
            individualService.insertAnswer(no, content, admin);
            // 성공 메시지 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("successMessage", "답변이 성공적으로 등록되었습니다.");
            return "redirect:/admin/individual/noanswer";
        } catch (NoAdminLoginException ex){
            // 예외 메시지를 RedirectAttributes에 추가
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 후 다시 진행하세요");
            return "/admin/individual/noanswer/detail/" + no;
        }
    }
    // 미완료 CS 끝



    // CS 완료 리스트
    @GetMapping("/admin/individual/answer")
    public String answerList(@RequestParam(defaultValue = "1", value = "page")int page,
                                @RequestParam(defaultValue = "10", value = "rows")int rows,
                                Model model){
        // 전체 행 수를 구하는 메소드
        int totalRows = individualService.getTotalRows();
        Pagination pagination = new Pagination(page, totalRows, rows);

        // Pagination을 사용하여 페이지의 데이터 리스트를 가져옴
        List<IndividualDto> answerList = individualService.getAnswerList(pagination);
        model.addAttribute("answerList", answerList);

        // 모델에 Pagination 추가
        model.addAttribute("pagination", pagination);
        return "admin/individual/answerlist";
    }

    // CS 완료 디테일
    @GetMapping("/admin/individual/answer/detail/{no}")
    public String answerDetail(@PathVariable Long no, Model model){
        // 댓글을 단 관리자의 아이디를 갖고 오기위한 과정(관리자의 아이디가 여러개를 가정했을 경우)
        InquiryComment answerInquiry = individualService.getAnswerNo(no);
        Inquiry noAnswerInquiry = individualService.getNoAnswerNo(no);
        Admin admin = new Admin();
        admin.setNo(answerInquiry.getAdmin().getNo());
        Admin saveId = adminMapper.getAdminNo(admin.getNo());

        // Model에 객체를 전송
        model.addAttribute("admin", saveId);
        model.addAttribute("answer", answerInquiry);
        model.addAttribute("noanswer", noAnswerInquiry);
        return "admin/individual/answerdetail";
    }

    @PostMapping("/admin/individual/answer/modify")
    public ResponseEntity<?> modifyAnswer(@RequestParam("no") Long answerNo, @RequestParam("content") String content, HttpSession session){
        Admin saveAdmin = (Admin) session.getAttribute("admin");
        Map<String, Object> response = new HashMap<>();

        if(saveAdmin == null){
            response.put("errorMessage", "로그인 후 다시 진행하세요");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401 Unauthorized
        }

        InquiryComment answerInquiry = individualService.getAnswerResponseNo(answerNo);
        if(answerInquiry.getAdmin().getNo() != saveAdmin.getNo()){
            response.put("errorMessage", "답글을 등록한 아이디와 현재 로그인된 아이디가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response); // 403 Forbidden
        }

        //model.addAttribute("answer", answerInquiry);
        individualService.updateAnswer(content, answerNo);
        response.put("successMessage", "답변이 성공적으로 수정되었습니다.");
        return ResponseEntity.ok(response); // 200 OK


    }

}

package com.example.bookhub.board.controller;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.dto.Post;
import com.example.bookhub.admin.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board/notice")
@RequiredArgsConstructor
public class NoticeController {

    private static final int START_OFFSET = 1;

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                            @RequestParam(name = "sort", required = false, defaultValue = "writer") String sort,
                            @ModelAttribute("filter") BoardFilter filter,
                            Model model) {

        int totalRows = boardService.getTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);

        if (totalRows > 0) {
            int begin = pagination.getBegin() - START_OFFSET;
            List<Post> posts = boardService.getPosts(filter, begin, rows, sort);
            model.addAttribute("posts", posts);
        } else {
            model.addAttribute("posts", List.of());
        }

        model.addAttribute("paging", pagination);
        model.addAttribute("filter", filter);

        return "board/notice/list";
    }
}

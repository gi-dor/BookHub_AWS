package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.OrderFilter;
import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.service.OrderService;
import com.example.bookhub.admin.vo.Return;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderController {

    private static final int START_OFFSET = 1;

    private final OrderService orderService;

    @GetMapping("/return/list")
    public String boardList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                            @RequestParam(name = "sort", required = false, defaultValue = "returnDate") String sort,
                            @ModelAttribute("filter") OrderFilter filter,
                            Model model) {

        if (filter.getStatus() == null) {
            List<String> status = new ArrayList<>();
            status.add("N");
            filter.setStatus(status);
        }

        int totalRows = orderService.getReturnTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);

        if (totalRows > 0) {
            int begin = pagination.getBegin() - START_OFFSET;
            List<Return> returns = orderService.getReturns(filter, begin, rows, sort);
            model.addAttribute("returns", returns);
        } else {
            model.addAttribute("returns", List.of());
        }

        model.addAttribute("paging", pagination);
        model.addAttribute("filter", filter);
        model.addAttribute("return", new Return());

        return "admin/order/return/list";
    }
}

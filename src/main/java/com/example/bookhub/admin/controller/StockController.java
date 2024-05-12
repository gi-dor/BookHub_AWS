package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.service.StockService;
import com.example.bookhub.admin.vo.Stock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/stock")
@RequiredArgsConstructor
public class StockController {

    private static final int START_OFFSET = 1;

    private final StockService stockService;

    @GetMapping("/notification/list")
    public String stock(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                        @ModelAttribute("filter") StockFilter filter,
                        Model model) {

        int totalRows = stockService.getTotalRows(filter);
        Pagination pagination = new Pagination(page, totalRows, rows);

        if (totalRows > 0) {
            int begin = pagination.getBegin() - START_OFFSET;
            List<Stock> stockNotifications = stockService.getStockNotifications(filter, begin, rows);
            model.addAttribute("stockNotifications", stockNotifications);
        } else {
            model.addAttribute("stockNotifications", List.of());
        }

        model.addAttribute("paging", pagination);
        model.addAttribute("filter", filter);

        return "admin/product/stock/list";
    }

}

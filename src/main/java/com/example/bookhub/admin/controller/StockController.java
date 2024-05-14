package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.Pagination;
import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.service.StockService;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.admin.vo.Stock;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/stock")
@RequiredArgsConstructor
public class StockController {

    private static final int START_OFFSET = 1;

    private final StockService stockService;

    @GetMapping("/notification/list")
    public String stock(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                        @ModelAttribute("filter") StockFilter filter, HttpSession session,
                        Model model) {
        // 비로그인 접근 차단
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

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

    @PostMapping("/notification/modify")
    @ResponseBody
    public void modifyStockNotification(@RequestBody Map<String, Object> requestData) {
        List<Long> checkedNos = (List<Long>) requestData.get("checkedNos");
        int modifiedStockNotification = Integer.parseInt((String) requestData.get("modifiedStockNotification"));

        stockService.modifyStockNotification(checkedNos, modifiedStockNotification);
    }

}

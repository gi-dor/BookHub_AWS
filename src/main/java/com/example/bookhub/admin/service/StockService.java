package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.mapper.StockMapper;
import com.example.bookhub.admin.vo.Stock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;

    public int getTotalRows(StockFilter filter) {
        return stockMapper.getTotalRows(filter);
    }

    public List<Stock> getStockNotifications(StockFilter filter, int offset, int limit) {
        return stockMapper.getStockNotifications(filter, offset, limit);
    }

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "sendNoticeContentAutomatically", lockAtLeastFor = "20s", lockAtMostFor = "50s")
    public void notifyInsufficientStock() {
        List<Long> bookNos = stockMapper.getBookNoBelowStockNotification();
        for (Long bookNo : bookNos) {
            stockMapper.updateStockNotification(bookNo);
        }
    }

}

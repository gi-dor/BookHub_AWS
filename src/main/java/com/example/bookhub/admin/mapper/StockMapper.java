package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.vo.Stock;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper {

    int getTotalRows(@Param("filter") StockFilter filter);

    List<Stock> getStockNotifications(@Param("filter") StockFilter filter, @Param("offset") int offset,
                                      @Param("limit") int limit);

    List<Long> getBookNoBelowStockNotification();

    void updateStockNotification(long bookNo);
}

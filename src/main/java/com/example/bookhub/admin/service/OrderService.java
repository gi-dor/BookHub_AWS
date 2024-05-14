package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.OrderFilter;
import com.example.bookhub.admin.mapper.OrderMapper;
import com.example.bookhub.admin.vo.Return;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public int getReturnTotalRows(OrderFilter filter) {
        return orderMapper.getReturnTotalRows(filter);
    }

    @Transactional(readOnly = true)
    public List<Return> getReturns(OrderFilter filter, int offset, int limit, String sort) {
        return orderMapper.getReturns(filter, offset, limit, sort);
    }

}

package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.OrderFilter;
import com.example.bookhub.admin.vo.Return;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    int getTotalRowsInNotice(@Param("filter") OrderFilter filter);

    List<Return> getReturns(@Param("filter") OrderFilter filter, @Param("offset") int offset,
                            @Param("limit") int limit, @Param("sort") String sort);

}

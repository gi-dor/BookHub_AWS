package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.DailyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailDashBoardMapper {


    List<DailyDto> getDetailDaily(String value);


}

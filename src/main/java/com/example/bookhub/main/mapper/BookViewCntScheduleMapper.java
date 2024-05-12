package com.example.bookhub.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookViewCntScheduleMapper {

    void updateBookViewCountForAll();



}

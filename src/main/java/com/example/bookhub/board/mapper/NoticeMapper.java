package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> findAllNotice();
}

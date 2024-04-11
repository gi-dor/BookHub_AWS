package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.NoticeMapper;
import com.example.bookhub.board.vo.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {


    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<Notice> findAllNotice() {
        return noticeMapper.findAllNotice();
    }

}

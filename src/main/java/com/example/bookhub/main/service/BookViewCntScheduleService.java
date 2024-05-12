package com.example.bookhub.main.service;

import com.example.bookhub.main.mapper.BookViewCntScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookViewCntScheduleService {

    private final BookViewCntScheduleMapper bookViewCntScheduleMapper;

    // 2시간 간격
    @Scheduled(fixedRate = 2 * 60 * 60 * 1000)
    public void updateBookViewCount() {
        // 모든 책의 조회수 업데이트
        bookViewCntScheduleMapper.updateBookViewCountForAll();
    }
}

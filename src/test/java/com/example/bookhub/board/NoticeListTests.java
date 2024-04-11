package com.example.bookhub.board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.bookhub.board.service.NoticeService;
import com.example.bookhub.board.vo.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NoticeListTests {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testFindAllNotices() {
        List<Notice> notices = noticeService.findAllNotice();
        assertThat(notices, is(notNullValue()));
        assertEquals(2, notices.size()); // 예상되는 공지사항 개수에 따라 변경
        assertEquals("Title 1", notices.get(0).getTitle());
        assertEquals("Content 1", notices.get(0).getContent());
        assertEquals("Title 2", notices.get(1).getTitle());
        assertEquals("Content 2", notices.get(1).getContent());
    }
}

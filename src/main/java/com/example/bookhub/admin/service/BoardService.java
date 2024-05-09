package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Posts;
import com.example.bookhub.admin.mapper.BoardMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int getTotalRows(BoardFilter filter) {
        return boardMapper.getTotalRows(filter);
    }

    public List<Posts> getPosts(BoardFilter filter, int offset, int limit, String sort) {
        String boardType = filter.getBoardType();

        if (boardType != null && boardType.equals("notice")) {
            return boardMapper.getNotices(filter, offset, limit, sort);
        }

        return boardMapper.getNotices(filter, offset, limit, sort);
    }

    public void deletePostByNo(List<Long> deletedPostNos) {
        for (Long deletedPostNo : deletedPostNos) {
            boardMapper.deletePostByNo(deletedPostNo);
        }
    }
}

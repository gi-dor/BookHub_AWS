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
        return boardMapper.getPosts(filter, offset, limit, sort);
    }
}

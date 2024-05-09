package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Post;
import com.example.bookhub.admin.mapper.BoardMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int getTotalRows(BoardFilter filter) {
        String boardType = filter.getBoardType();

        if (boardType != null && boardType.equals("notice")) {
            return boardMapper.getTotalRowsInNotice(filter);
        }

        return 0;
    }

    public List<Post> getPosts(BoardFilter filter, int offset, int limit, String sort) {
        String boardType = filter.getBoardType();

        if (boardType != null && boardType.equals("notice")) {
            return boardMapper.getNotices(filter, offset, limit, sort);
        }

        return new ArrayList<>();
    }

    public void deletePostByNo(List<Long> deletedPostNos) {
        for (Long deletedPostNo : deletedPostNos) {
            boardMapper.deletePostByNo(deletedPostNo);
        }
    }

    public void createPost(Post createdPost) {
        String boardType = createdPost.getBoardType();
        String important = createdPost.getImportant();

        if (important == null) {
            createdPost.setImportant("N");
        }

        if (boardType != null && boardType.equals("notice")) {
            boardMapper.registerNotice(createdPost);
        }
    }

    public Post getPostByNo(long postNo) {
        return boardMapper.getPostByNo(postNo);
    }
}

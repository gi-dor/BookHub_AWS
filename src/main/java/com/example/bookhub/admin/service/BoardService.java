package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Post;
import com.example.bookhub.admin.mapper.BoardMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Post post = new Post();
        post = createdPost;
        String boardType = post.getBoardType();
        String important = post.getImportant();

        if (important == null) {
            post.setImportant("N");
        }

        if (boardType != null && boardType.equals("notice")) {
            boardMapper.registerNotice(post);
        }
    }

    public Post getPostByNo(long postNo) {
        return boardMapper.getPostByNo(postNo);
    }

    public void modifyPost(Post modifiedPost) {
        Post post = new Post();
        post = modifiedPost;
        String important = post.getImportant();

        if (important == null) {
            post.setImportant("N");
        }

        boardMapper.modifyPost(post);
    }

    public Post getNoticeByNo(long postNo) {
        return boardMapper.getNoticeByNo(postNo);
    }

    public void increaseViewCount(long postNo) {
        boardMapper.increaseViewCount(postNo);
    }

    public List<Post> getNoticesByNo(long postNo, BoardFilter filter) {
        return boardMapper.getNoticesByNo(postNo, filter);
    }
}

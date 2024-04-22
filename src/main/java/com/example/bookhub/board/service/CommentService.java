package com.example.bookhub.board.service;

import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.board.mapper.CommentMapper;
import com.example.bookhub.board.mapper.CommunityMapper;
import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityComment;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    /**
     * 페이징 처리된 게시글의 댓글 목록 조회
     * @param page
     * @param size
     * @param communityNo
     * @return
     */
    public List<CommunityComment> findByCommunityNoComment(int page, int size, Long communityNo) {
        Community community = new Community();
        community.setNo(communityNo);

        CommunityComment communityComment = new CommunityComment();
        communityComment.setCommunity(community);

        int offset = (page - 1) * size;
        return commentMapper.findByCommunityNoComment(offset, size, communityNo);
    }

    /**
     * 게시글의 댓글 개수를 조회
     * @param communityNo
     * @return
     */
    public int getCommentCount(Long communityNo) {
        return commentMapper.getCommentCount(communityNo);
    }

    /**
     * 댓글 등록
     * @param communityNo
     * @param userId
     * @param content
     */
    public void addComment(Long communityNo, String userId, String content) {
        User user = userMapper.selectUserById(userId);
        Community community = new Community();
        community.setNo(communityNo);

        CommunityComment communityComment = new CommunityComment();

        communityComment.setCommunity(community);
        communityComment.setUser(user);
        communityComment.setContent(content);

        commentMapper.addComment(communityComment);
    }

    public void updateComment(CommunityComment comment) {
        commentMapper.updateComment(comment);
    }

    public void deleteComment(Long no) {
        commentMapper.deleteComment(no);
    }
}

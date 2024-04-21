package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 페이징 처리된 게시글의 댓글 목록 조회
     * @param offset
     * @param size
     * @param community
     * @return
     */
    List<CommunityComment> findByCommunityNoComment(@Param("offset") int offset, @Param("size") int size, CommunityComment communityComment);

    /**
     * 게시글의 댓글 개수 조회
     * @param community
     * @return
     */
    int getCommentCount(CommunityComment communityComment);


    /**
     * 댓글 등록
     * @param comment
     */
    void addComment(CommunityComment comment);

}

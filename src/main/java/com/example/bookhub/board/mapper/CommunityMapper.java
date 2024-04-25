package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityImages;
import com.example.bookhub.user.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityMapper {

    /**
     * 페이징 처리된 공지사항 목록 조회
     * @param offset
     * @return
     */
    List<Community> findAllCommunity(@Param("keyword") String keyword, @Param("offset") int offset);

    /**
     * 전체 공지사항 개수 조회
     * @return
     */
    int getTotalCommunitiesCount(String keyword);

    /**
     * 유저의 번호로 유저 조회
     * @param userNo
     * @return
     */
    User getUserByNo(Long userNo);

    /**
     * 커뮤니티 게시글 등록
     * @param community
     */
    void insertCommunity(Community community);

    void insertCommunityImage(CommunityImages communityImages);



    /**
     * 게시글 번호로 게시글 조회
     * @param no 게시글 번호
     * @return
     */
    Community getCommunityByNo(Long no);



    /**
     * 커뮤니티 게시글 수정
     * @param community
     */
    void updateCommunity(Community community);

    /**
     * 커뮤니티 게시글 삭제
     * @param no
     */
    void deleteCommunity(Long no);

    /**
     * 조회수 증가
     * @param no
     * @return
     */
    void viewCount(Long no);


}

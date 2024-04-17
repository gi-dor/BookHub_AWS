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
     * @param size
     * @return
     */
    List<Community> findAllCommunity(@Param("offset") int offset, @Param("size") int size);

    /**
     * 전체 공지사항 개수 조회
     * @return
     */
    int getTotalCommunitiesCount();

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
     * 게시글 제목 혹은 내용으로 검색
     * @param keyword 검색 키워드
     * @return
     */
    List<Community> searchCommunityByTitleOrContent(String keyword);

    /**
     * 게시글 제목으로 검색
     * @param keyword
     * @return
     */
    List<Community> searchCommunityByTitle(String keyword);

    /**
     * 게시글 내용으로 검색
     * @param keyword
     * @return
     */
    List<Community> searchCommunityByContent(String keyword);


}

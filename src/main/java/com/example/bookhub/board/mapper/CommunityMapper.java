package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Community;
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
    
    void insertCommunity(Community community);



}

package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.CommunityMapper;
import com.example.bookhub.board.vo.Community;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper communityMapper;

    /**
     * 페이징 처리된 커뮤니티 목록을 조회
     * @return
     */
    public List<Community> findAllCommunity(int page, int size) {
        int offset = (page - 1) * size;
        return communityMapper.findAllCommunity(offset, size);
    }

    /**
     * 전체 커뮤니티 게시글 개수를 조회
     * @return
     */
    public int getTotalCommunitiesCount() {
        return communityMapper.getTotalCommunitiesCount();
    }

    /**
     * 커뮤니티 게시글을 등록
     * @param title 공지사항 제목
     * @param content 공지사항 내용
     */
    public void insertCommunity(String title, String content) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);

        communityMapper.insertCommunity(community);
    }

    /**
     * user의 번호로 유저의 이름을 조회
     * @param userNo
     * @return
     */
    public User getUserByNo(Long userNo) {return communityMapper.getUserByNo(userNo);}

    /**
     * 게시글의 번호로 게시글 조회
     * @param no
     * @return
     */
    public Community getCommunityByNo(Long no) {
        return communityMapper.getCommunityByNo(no);
    }

    /**
     * 커뮤니티 게시글 수정
     * @param community
     */
    public void updateCommunity(Community community) {
        communityMapper.updateCommunity(community);
    }

    /**
     * 커뮤니티 게시글 삭제
     * @param no
     */
    public void deleteCommunity(Long no) {
        communityMapper.deleteCommunity(no);
    }

    /**
     * 게시글 검색
     * @param keyword
     * @return
     */
    public List<Community> searchCommunity(String keyword, String searchOption) {

        if (searchOption.equals("title")) {
            return communityMapper.searchCommunityByTitle(keyword);
        } else if (searchOption.equals("content")) {
            return communityMapper.searchCommunityByContent(keyword);
        } else if (searchOption.equals("titleContent")) {
            return communityMapper.searchCommunityByTitleOrContent(keyword);
        } else {
            return new ArrayList<>();
        }


    }
}


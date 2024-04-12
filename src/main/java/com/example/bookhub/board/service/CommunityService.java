package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.CommunityMapper;
import com.example.bookhub.board.vo.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper communityMapper;

    /**
     * 페이징 처리된 공지사항 목록을 조회
     * @return
     */
    public List<Community> findAllCommunity(int page, int size) {
        int offset = (page - 1) * size;
        return communityMapper.findAllCommunity(offset, size);
    }

    /**
     * 전체 공지사항 개수를 조회
     * @return
     */
    public int getTotalCommunitiesCount() {
        return communityMapper.getTotalCommunitiesCount();
    }




    /**
     * 공지사항 게시글을 등록
     * @param title 공지사항 제목
     * @param content 공지사항 내용
     */
    public void insertNotice(String title, String content) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);

        communityMapper.insertCommunity(community);
    }

}

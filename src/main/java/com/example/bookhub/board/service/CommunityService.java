package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.CommunityMapper;
import com.example.bookhub.board.vo.Community;
import com.example.bookhub.board.vo.CommunityImages;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper communityMapper;
    private final UserMapper userMapper;

    @Value("${image.upload.dir}")
    private String uploadDir;


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
     *
     * @param title   공지사항 제목
     * @param content 공지사항 내용
     * @param userId
     */
    @Transactional
    public void insertCommunity(String title, String content, String userId, List<MultipartFile> images) {
        User user = userMapper.selectUserById(userId);

        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);
        community.setUser(user);
        communityMapper.insertCommunity(community);

        if(!CollectionUtils.isEmpty(images)) {
            for (MultipartFile image : images) {
                String imagePath = saveImage(image);

                CommunityImages communityImages = new CommunityImages();
                communityImages.setCommunity(community);
                communityImages.setImagePath(imagePath);
                communityMapper.insertCommunityImage(communityImages);
            }
        }
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
    // enum 클래스로 코딩해보기
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

    public String saveImage(MultipartFile image) {
        try {
            // 이미지를 저장할 디렉토리 생성
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 이미지 파일의 고유한 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            // 이미지를 저장할 경로 생성
            Path filePath = Paths.get(uploadDir + fileName);

            // 이미지를 저장
            Files.write(filePath, image.getBytes());

            // 저장된 이미지 파일의 경로 반환
            return fileName;
        } catch (IOException e) {
            // 이미지 저장 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
    }




}


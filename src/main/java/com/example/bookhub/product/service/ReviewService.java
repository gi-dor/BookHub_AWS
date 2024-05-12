package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.*;
import com.example.bookhub.product.mapper.ReviewMapper;
import com.example.bookhub.product.vo.*;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Value("${review.image.save.directory}")   //@Value - 설정파일의 설정정보 값을 주입해줌
    private String saveDirectory;

    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Transactional
    public long createReview(ReviewForm reviewForm, String userId) {

        User user = userMapper.selectUserById(userId);

        ReviewTag reviewTag = new ReviewTag();
        reviewTag.setReviewTagNo(reviewForm.getReviewTagNo());

        Book book = new Book();
        book.setBookNo(reviewForm.getBookNo());

        Review review = Review.builder()
                .comment(reviewForm.getComment())
                .rate(reviewForm.getRate())
                .user(user)
                .reviewTag(reviewTag)
                .book(book)
                .buyerYn(reviewForm.getBuyerYn())
                .build();

        reviewMapper.createReview(review);
        long generatedReviewNo = review.getReviewNo();

        reviewMapper.updateReviewCount(reviewForm.getBookNo());
        reviewMapper.updateBookAverageRating(reviewForm.getBookNo(), reviewForm.getRate());


        if(!reviewForm.getImageList().isEmpty())
            for(MultipartFile image : reviewForm.getImageList()){
                if (!image.isEmpty()) {
                    String imagePath = uploadImage(image);

                    ReviewImage reviewImage = new ReviewImage();
                    reviewImage.setImagePath(imagePath);
                    reviewImage.setReview(review);
                    reviewMapper.createReviewImage(reviewImage);
                }
            }

        return generatedReviewNo;
    }

    @Cacheable(value = "ReviewMapper.getReviewsByBookNo", key = "#bookNo + '-' + #page", condition = "#page <= 3")
    @Transactional(readOnly = true)
    public ReviewListDto getReviewsByBookNo(long bookNo, String userId, int page, String sort, String option) {
        long userNo = 0;
        if(!"guest".equals(userId)) {
            User user = userMapper.selectUserById(userId);
            userNo = user.getNo();
        }

        Map map = new HashMap<String, Object>();
        map.put("bookNo", bookNo);
        map.put("userNo", userNo);
        map.put("sort", sort);
        map.put("option", option);

        int totalRows = reviewMapper.getReviewTotalRows(map);
        Pagination pagination = new Pagination(page, totalRows);

        int offset = 0;
        if(totalRows > 0) {
            offset = pagination.getBegin() - 1;
        }
        map.put("offset", offset);

        List<ReviewDto> reviewDtoList = reviewMapper.getReviewsByBookNo(map);

        ReviewListDto reviewListDto = new ReviewListDto(reviewDtoList, pagination);
        return reviewListDto;
    }

    public String uploadImage(MultipartFile image) {

//        if(uploadFile.isEmpty()) {
//            return "default.png";
//        }

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        File file = new File(saveDirectory, fileName);

        try {
            FileCopyUtils.copy(image.getBytes(), file);
        } catch(IOException ex) {
            throw new RuntimeException("첨부파일을 저장할 수 없습니다", ex);
        }

        return fileName;
    }

    public String recommend(long reviewNo, String userId) {

        ReviewRecommendUser reviewRecommendUser = new ReviewRecommendUser();

        Review review = new Review();
        review.setReviewNo(reviewNo);
        reviewRecommendUser.setReview(review);

        User user = userMapper.selectUserById(userId);
        reviewRecommendUser.setUser(user);

        // 이미 추천했는지 여부 확인
        Optional<ReviewRecommendUser> optional = reviewMapper.getByReviewNoAndUserNo(reviewNo, user.getNo());

        if(optional.isEmpty()){
            String recommendStatus = "recommend";
            reviewMapper.createReviewRecommendUser(reviewRecommendUser);
            reviewMapper.updateReviewRecommendCount(reviewNo, recommendStatus);
            return recommendStatus;
        }
        else{
            String recommendStatus = "cancel";
            reviewMapper.deleteReviewRecommendUser(reviewRecommendUser);
            reviewMapper.updateReviewRecommendCount(reviewNo, recommendStatus);
            return recommendStatus;
        }
    }

    public long createReviewReply(ReviewReplyForm reviewReplyForm, String userId) {

        Review review = new Review();
        review.setReviewNo(reviewReplyForm.getReviewNo());

        User user = userMapper.selectUserById(userId);

        ReviewReply reviewReply = ReviewReply.builder()
                .comment(reviewReplyForm.getComment())
                .review(review)
                .user(user)
                .build();

        reviewMapper.createReviewReply(reviewReply);
        reviewMapper.updateReviewReplyCount(reviewReplyForm.getReviewNo(), "create");

        return reviewReply.getReviewReplyNo();
    }

    public Review getReviewByReviewNo(long reviewNo) {
        return reviewMapper.getReviewByReviewNo(reviewNo);
    }

    public void modifyReview(ReviewForm reviewForm) {
        Review review = reviewMapper.getReviewByReviewNo(reviewForm.getReviewNo());

        review.setRate(reviewForm.getRate());

        ReviewTag reviewTag = new ReviewTag();
        reviewTag.setReviewTagNo(reviewForm.getReviewTagNo());
        review.setReviewTag(reviewTag);

        review.setComment(reviewForm.getComment());

        reviewMapper.updateReview(review);

        if(!reviewForm.getImageList().isEmpty())
            reviewMapper.deleteReviewImageByReviewNo(reviewForm.getReviewNo());
            for(MultipartFile image : reviewForm.getImageList()){
                if (!image.isEmpty()) {

                    String imagePath = uploadImage(image);

                    ReviewImage reviewImage = new ReviewImage();
                    reviewImage.setImagePath(imagePath);
                    reviewImage.setReview(review);
                    reviewMapper.createReviewImage(reviewImage);
                }
            }
    }

    public void deleteReview(long reviewNo) {
        reviewMapper.deleteReview(reviewNo);
    }

    @Transactional(readOnly = true)
    public List<Integer> getRate(long bookNo) {
        List<Integer> rateCountList = new ArrayList<>();
        for(int start = 0; start <= 4; start++){
            int rateCount = reviewMapper.getRateCount(bookNo, start, start + 1);
            rateCountList.add(rateCount);
        }
        return rateCountList;
    }

    @Transactional(readOnly = true)
    public List<Integer> getReviewTagCount(long bookNo){
        List<Integer> reviewTagCountList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            int reviewTagNo = i;
            int reviewTagCount = reviewMapper.getReviewTagCount(bookNo, reviewTagNo);
            reviewTagCountList.add(reviewTagCount);
        }
        return reviewTagCountList;
    }

    public void modifyReviewReply(ReviewReplyForm reviewReplyForm) {
        reviewMapper.modifyReviewReply(reviewReplyForm.getReviewReplyNo(), reviewReplyForm.getComment());
    }

    public void deleteReviewReply(long reviewReplyNo, long reviewNo) {
        reviewMapper.deleteReviewReply(reviewReplyNo);
        reviewMapper.updateReviewReplyCount(reviewNo, "delete");
    }
}

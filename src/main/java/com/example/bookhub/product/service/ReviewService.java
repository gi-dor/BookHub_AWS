package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.dto.ReviewDto;
import com.example.bookhub.product.dto.ReviewReplyForm;
import com.example.bookhub.product.mapper.ReviewMapper;
import com.example.bookhub.product.vo.*;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Value("${review.image.save.directory}")   //@Value - 설정파일의 설정정보 값을 주입해줌
    private String saveDirectory;

    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;

    public void createReview(ReviewForm reviewForm, String userId) {

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
                .build();

        reviewMapper.createReview(review);
        long generatedReviewNo = review.getReviewNo();
        System.out.println("generatedReviewNo: " + generatedReviewNo);


        if(!reviewForm.getImageList().isEmpty())
            for(MultipartFile image : reviewForm.getImageList()){
                System.out.println(image);
                String imagePath = uploadImage(image);

                ReviewImage reviewImage = new ReviewImage();
                reviewImage.setImagePath(imagePath);
                reviewImage.setReview(review);
                reviewMapper.createReviewImage(reviewImage);
            }
    }

    public List<ReviewDto> getReviewsByBookNo(long bookNo, String userId) {

        User user = userMapper.selectUserById(userId);
        return reviewMapper.getReviewsByBookNo(bookNo, user.getNo());
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

    public void createReviewReply(ReviewReplyForm reviewReplyForm, String userId) {

        Review review = new Review();
        review.setReviewNo(reviewReplyForm.getReviewNo());

        User user = userMapper.selectUserById(userId);

        ReviewReply reviewReply = ReviewReply.builder()
                .comment(reviewReplyForm.getComment())
                .review(review)
                .user(user)
                .build();

        reviewMapper.createReviewReply(reviewReply);
    }
}

package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.ReviewForm;
import com.example.bookhub.product.mapper.ReviewMapper;
import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.Review;
import com.example.bookhub.product.vo.ReviewImage;
import com.example.bookhub.product.vo.ReviewTag;
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

    public List<Review> getReviewsByBookNo(long bookNo) {
        return reviewMapper.getReviewsByBookNo(bookNo);
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
}

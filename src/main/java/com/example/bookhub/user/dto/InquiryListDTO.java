package com.example.bookhub.user.dto;

import com.example.bookhub.board.vo.FaqCategory;
import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserPagination;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InquiryListDTO {

    Long no;
    FaqCategory faqCategory;
    String title;
    String content;
    boolean answerYn;
    boolean deleteYn;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    User user;

}

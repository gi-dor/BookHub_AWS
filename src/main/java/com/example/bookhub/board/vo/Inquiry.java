package com.example.bookhub.board.vo;

import com.example.bookhub.user.vo.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

    private Long no;
    private FaqCategory faqCategory;
    private User user;
    private String title;
    private String content;
    private boolean answerYn;
    private boolean deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


}


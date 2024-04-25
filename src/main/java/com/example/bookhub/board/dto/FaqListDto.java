package com.example.bookhub.board.dto;

import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.vo.Faq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqListDto {

    private List<Faq> faqs;
    private Pagination pagination;

}

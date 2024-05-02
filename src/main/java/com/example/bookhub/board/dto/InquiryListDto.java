package com.example.bookhub.board.dto;

import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.vo.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryListDto {

    private List<Inquiry> inquiries;
    private Pagination pagination;
}

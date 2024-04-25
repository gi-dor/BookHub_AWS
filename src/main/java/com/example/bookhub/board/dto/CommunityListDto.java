package com.example.bookhub.board.dto;

import com.example.bookhub.board.Pagination;
import com.example.bookhub.board.vo.Community;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityListDto {

    private List<Community> communities;
    private Pagination pagination;

}

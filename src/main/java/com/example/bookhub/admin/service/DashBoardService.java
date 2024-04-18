package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.DashBoardMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DashBoardMapper dashBoardMapper;

    public int getAllUserCnt() {
        return dashBoardMapper.getAllUserCnt();
    }

    public int getAllBookCnt(){
        return dashBoardMapper.getAllBookCnt();
    }

}

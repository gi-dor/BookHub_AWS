package com.example.bookhub.admin.mapper;

import com.example.bookhub.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashBoardMapper {

    int getAllUserCnt();
}

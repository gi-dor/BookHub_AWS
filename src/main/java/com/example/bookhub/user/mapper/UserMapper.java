package com.example.bookhub.user.mapper;

import com.example.bookhub.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    void insertUser(User user);

    User selectUserByNo(Long no);

    User selectUserById(String id);

    User selectUserByEmail(String email);

     int idCheck(String id);

     int emailCheck(String email);

     void updateUser(User user);

}

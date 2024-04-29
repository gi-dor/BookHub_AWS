package com.example.bookhub.user.mapper;

import com.example.bookhub.user.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface UserMapper {


    void insertUser(User user);

    User selectUserByNo(Long no);

    User selectUserById(String id);

     int idCheck(String id);

     int emailCheck(String email);

     void updateUser(User user);

     User selectUserByIdAndEmail(@Param("id") String id , @Param("email") String email);

    void updateResetPassword(@Param("id") String id, @Param("password") String password);
}

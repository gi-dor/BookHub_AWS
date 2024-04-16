package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.vo.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Admin getAdminId(String id);
    Admin getAdminEmail(String email);
    void join(Admin admin);
}

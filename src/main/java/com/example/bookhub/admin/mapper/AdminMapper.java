package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.vo.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Admin getAdmin(String id);
    void join(Admin admin);
}

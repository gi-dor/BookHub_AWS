package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Posts;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

    int getTotalRows(@Param("filter") BoardFilter filter);

    List<Posts> getNotices(@Param("filter") BoardFilter filter, @Param("offset") int offset,
                           @Param("limit") int limit, @Param("sort") String sort);

    void deletePostByNo(Long deletedPostNo);
}

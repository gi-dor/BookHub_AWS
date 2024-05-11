package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.BoardFilter;
import com.example.bookhub.admin.dto.Post;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

    int getTotalRowsInNotice(@Param("filter") BoardFilter filter);

    List<Post> getNotices(@Param("filter") BoardFilter filter, @Param("offset") int offset,
                          @Param("limit") int limit, @Param("sort") String sort);

    void deletePostByNo(Long deletedPostNo);

    void registerNotice(Post createdPost);

    Post getPostByNo(long postNo);

    void modifyPost(Post modifiedPost);

    Post getNoticeByNo(long postNo);

    void increaseViewCount(long postNo);

    List<Post> getNoticesByNo(long postNo);
}

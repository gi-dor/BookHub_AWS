package com.example.bookhub.admin.mapper;

import com.example.bookhub.product.vo.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    int getTotalRows(@Param("opt") String opt, @Param("keyword") String keyword);

    List<Book> getBooks(@Param("opt") String opt, @Param("keyword") String keyword, @Param("offset") int offset,
                        @Param("limit") int limit, @Param("sort") String sort);
}

package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.BookList;
import com.example.bookhub.admin.dto.Product;
import com.example.bookhub.admin.dto.ProductFilter;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    int getTotalRows(@Param("filter") ProductFilter filter);

    List<BookList> getBooks(@Param("filter") ProductFilter filter, @Param("offset") int offset,
                            @Param("limit") int limit, @Param("sort") String sort);

    List<Publisher> getPublishers();

    void deleteProductByNo(Long deletedProductNo);

    Product getProductByNo(Long productNo);

    Long getSuperCategoryNoBySubCategoryNo(Long categoryNo);

    void modifyProduct(Product modifiedProduct);
}

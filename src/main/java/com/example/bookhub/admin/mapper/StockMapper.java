package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.Product;
import com.example.bookhub.admin.dto.StockFilter;
import com.example.bookhub.admin.vo.Stock;
import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Publisher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper {

    int getTotalRows(@Param("filter") StockFilter filter);

    List<Stock> getStockNotifications(@Param("filter") StockFilter filter, @Param("offset") int offset,
                                      @Param("limit") int limit);

    List<Publisher> getPublishers();

    List<Author> getAuthors();

    void deleteProductByNo(Long deletedProductNo);

    Product getProductByNo(Long productNo);

    Long getSuperCategoryNoBySubCategoryNo(Long categoryNo);

    void modifyProduct(Product modifiedProduct);

    void registerProduct(Product product);

    void registerImage(Product product);

}

package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.vo.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    List<Category> getAllTopLevelCategories();

    List<Category> getAllSecondLevelCategories();

    List<Category> getAllThirdLevelCategories();

    List<Category> getSecondLevelCategoriesByTopLevelCategoryName(int categoryNo);
}

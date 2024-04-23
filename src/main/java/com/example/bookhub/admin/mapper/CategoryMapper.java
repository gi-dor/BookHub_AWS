package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.vo.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    List<Category> getAllTopLevelCategories();

    List<Category> getAllSecondLevelCategories();

    List<Category> getAllThirdLevelCategories();

    List<Category> getSubCategoriesByCategoryNo(int categoryNo);

    Category getParentCategoryByCategoryNo(int categoryNo);

    void addTopLevelCategory(String categoryName);

    void addSubCategory(String categoryName, int categoryNo);

    Category getTopLevelCategoryByCategoryName(String categoryName);

    Category getSubLevelCategoryByCategoryNameAndSuperCategoryNo(String categoryName, int categoryNo);

    String findCategoryByName(String categoryName);

    void modifyThirdCategory(int targetCategoryNo, int parentCategoryNo, String thirdCategoryName);

    void modifySecondCategory(int targetCategoryNo, int parentCategoryNo, String secondCategoryName);

    void modifyTopCategory(int targetCategoryNo, String topCategoryName);

    void deleteThirdCategory(int targetCategoryNo);

    void deleteSecondCategory(int targetCategoryNo);

    void deleteThirdCategoryBySecondCategoryNo(int targetCategoryNo);

    void deleteTopCategory(int targetCategoryNo);
}

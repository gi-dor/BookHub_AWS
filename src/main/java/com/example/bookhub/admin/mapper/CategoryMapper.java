package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.vo.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    List<Category> getAllTopLevelCategories();

    List<Category> getAllSecondLevelCategories();

    List<Category> getAllThirdLevelCategories();

    List<Category> getSubCategoriesByCategoryNo(long categoryNo);

    Category getParentCategoryByCategoryNo(long categoryNo);

    void addTopLevelCategory(String categoryName);

    void addSubCategory(String categoryName, long categoryNo);

    Category getTopLevelCategoryByCategoryName(String categoryName);

    Category getSubLevelCategoryByCategoryNameAndSuperCategoryNo(String categoryName, long categoryNo);

    Category findCategoryByCategoryNameAndCategoryNo(String categoryName, long categoryNo);

    void modifyThirdCategory(long targetCategoryNo, long parentCategoryNo, String thirdCategoryName);

    void modifySecondCategory(long targetCategoryNo, long parentCategoryNo, String secondCategoryName);

    void modifyTopCategory(long targetCategoryNo, String topCategoryName);

    void deleteThirdCategory(long targetCategoryNo);

    void deleteSecondCategory(long targetCategoryNo);

    void deleteThirdCategoryBySecondCategoryNo(long targetCategoryNo);

    void deleteTopCategory(long targetCategoryNo);
}

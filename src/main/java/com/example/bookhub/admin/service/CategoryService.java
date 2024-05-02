package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.CategoryMapper;
import com.example.bookhub.admin.validator.Validator;
import com.example.bookhub.admin.vo.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    public List<Category> getAllTopLevelCategories() {
        return categoryMapper.getAllTopLevelCategories();
    }

    public List<Category> getAllSecondLevelCategories() {
        return categoryMapper.getAllSecondLevelCategories();
    }

    public List<Category> getAllThirdLevelCategories() {
        return categoryMapper.getAllThirdLevelCategories();
    }

    public List<Category> getSubCategoriesByCategoryNo(long categoryNo) {
        return categoryMapper.getSubCategoriesByCategoryNo(categoryNo);
    }

    public Category getParentCategoryByCategoryNo(long categoryNo) {
        return categoryMapper.getParentCategoryByCategoryNo(categoryNo);
    }

    public void addTopLevelCategory(String categoryName) {
        Validator.isCategoryNameUnique(categoryName, null, categoryMapper);
        categoryMapper.addTopLevelCategory(categoryName);
    }

    public void addSubCategory(String categoryName, long categoryNo) {
        Validator.isCategoryNameUnique(categoryName, categoryNo, categoryMapper);
        categoryMapper.addSubCategory(categoryName, categoryNo);
    }

    public Category getTopLevelCategoryByCategoryName(String categoryName) {
        return categoryMapper.getTopLevelCategoryByCategoryName(categoryName);
    }

    public Category getSubLevelCategoryByCategoryNameAndSuperCategoryNo(String categoryName, long categoryNo) {
        return categoryMapper.getSubLevelCategoryByCategoryNameAndSuperCategoryNo(categoryName, categoryNo);
    }

    public void modifyThirdCategory(long targetCategoryNo, long parentCategoryNo, String thirdCategoryName) {
        Validator.isCategoryNameUnique(thirdCategoryName, parentCategoryNo, categoryMapper);
        categoryMapper.modifyThirdCategory(targetCategoryNo, parentCategoryNo, thirdCategoryName);
    }

    public void modifySecondCategory(long targetCategoryNo, long parentCategoryNo, String secondCategoryName) {
        Validator.isCategoryNameUnique(secondCategoryName, parentCategoryNo, categoryMapper);
        categoryMapper.modifySecondCategory(targetCategoryNo, parentCategoryNo, secondCategoryName);
    }

    public void modifyTopCategory(long targetCategoryNo, String topCategoryName) {
        Validator.isCategoryNameUnique(topCategoryName, null, categoryMapper);
        categoryMapper.modifyTopCategory(targetCategoryNo, topCategoryName);
    }

    public void deleteThirdCategory(long targetCategoryNo) {
        categoryMapper.deleteThirdCategory(targetCategoryNo);
    }

    public void deleteSecondCategory(long targetCategoryNo) {
        categoryMapper.deleteThirdCategoryBySecondCategoryNo(targetCategoryNo);
        categoryMapper.deleteSecondCategory(targetCategoryNo);
    }

    public void deleteTopCategory(long targetCategoryNo) {
        List<Category> secondCategories = categoryMapper.getSubCategoriesByCategoryNo(targetCategoryNo);

        for (Category secondCategory : secondCategories) {
            deleteSecondCategory(secondCategory.getNo());
        }

        categoryMapper.deleteTopCategory(targetCategoryNo);
    }

    public List<Category> getTotalSubCategories(long categoryNo) {
        List<Category> categories = categoryMapper.getSubCategoriesByCategoryNo(categoryNo);
        List<Category> totalSubCategories = new ArrayList<>(categories);
        for (Category category : categories) {
            totalSubCategories.addAll(categoryMapper.getSubCategoriesByCategoryNo(category.getNo()));
        }
        return totalSubCategories;
    }
}

package com.example.bookhub.admin.validator;

import com.example.bookhub.admin.mapper.CategoryMapper;

public class Validator {
    public static void isCategoryNameUnique(String categoryName, Long categoryNo, CategoryMapper categoryMapper) {
        if (categoryMapper.findCategoryByCategoryNameAndCategoryNo(categoryName, categoryNo) != null) {
            throw new IllegalArgumentException("카테고리 이름이 중복되었습니다.");
        }
    }
}

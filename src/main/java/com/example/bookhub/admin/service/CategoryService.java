package com.example.bookhub.admin.service;

import com.example.bookhub.admin.mapper.CategoryMapper;
import com.example.bookhub.admin.vo.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    public List<Category> getAllMajorCategories() {
        return categoryMapper.getAllMajorCategories();
    }

    public List<Category> getAllMiddleCategories() {
        return categoryMapper.getAllMiddleCategories();
    }
}

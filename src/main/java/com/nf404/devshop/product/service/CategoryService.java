package com.nf404.devshop.product.service;

import com.nf404.devshop.product.dto.res.CategoryDto;
import com.nf404.devshop.product.repository.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getMainCategory() {
        return categoryMapper.getMainCategory();
    }

    public List<CategoryDto> getSubCategoryByParentId(int parentId) {
        return categoryMapper.getByRefCategoryCode(parentId);
    }

    @Transactional
    public boolean addCategory(CategoryDto categoryDto) {
        categoryMapper.insertCategory(categoryDto);
        return true;
    }
}

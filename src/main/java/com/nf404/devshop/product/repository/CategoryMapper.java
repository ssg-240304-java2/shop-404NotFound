package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.model.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> selectMainCategories();

    List<CategoryDto> selectSubCategoriesByParentCategoryCode(int parentId);

    List<CategoryDto> selectAllCategories();

    void insertCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);
}

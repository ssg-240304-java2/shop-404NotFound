package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.dto.res.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> getMainCategory();

    List<CategoryDto> getByRefCategoryCode(int parentId);

    List<CategoryDto> selectAllCategories();

    void insertCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);
}

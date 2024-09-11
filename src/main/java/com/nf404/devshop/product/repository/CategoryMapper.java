package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.model.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> selectMainCategories();

    List<CategoryDto> selectSubCategoriesByParentCategoryCode(int parentId);

    List<CategoryDto> selectAllCategories();

    void insertCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    int countCategoriesByName(String categoryName);

    void deleteCategory(int categoryCode);

    int countCategoriesByNameAndCode(@Param("categoryName") String categoryName, @Param("categoryCode") int categoryCode);

    CategoryDto selectCategoryByCode(int categoryCode);

    int countSubCategoriesByCode(int categoryCode);
}

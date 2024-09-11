package com.nf404.devshop.product.service;

import com.nf404.devshop.product.model.dto.CategoryDto;
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

    /***
     * 메인 카테고리 반환 서비스 메소드
     * @return
     */
    public List<CategoryDto> getMainCategory() {
        return categoryMapper.selectMainCategories();
    }

    /***
     * 메인 카테고리에 따른 서브 카테고리 반환 서비스 메소드
     * @param parentId
     * @return
     */
    public List<CategoryDto> getSubCategoryByParentId(int parentId) {
        return categoryMapper.selectSubCategoriesByParentCategoryCode(parentId);
    }

    /***
     * 카테고리 정보 반환 서비스 메소드
     * @param categoryCode
     * @return
     */
    public CategoryDto getCategoryByCode(int categoryCode) {
        return categoryMapper.selectCategoryByCode(categoryCode);
    }

    /***
     * 전체 카테고리 반환 서비스 메소드
     * @return
     */
    public List<CategoryDto> getAllCategories() {
        return categoryMapper.selectAllCategories();
    }

    /***
     * 카테고리 생성 서비스 메소드
     * @param categoryDto
     * @return
     */
    @Transactional
    public void addCategory(CategoryDto categoryDto) {
        categoryMapper.insertCategory(categoryDto);
    }

    /***
     * 카테고리 정보 변경 메소드
     * @param categoryDto
     * @return
     */
    @Transactional
    public void updateCategory(CategoryDto categoryDto) {
        categoryMapper.updateCategory(categoryDto);
    }

    /***
     * 카테고리 삭제 메소드
     * @param categoryCode
     */
    @Transactional
    public void deleteCategory(int categoryCode) {
        categoryMapper.deleteCategory(categoryCode);
    }



    public boolean isCategoryNameDuplicated(String categoryName) {
        return categoryMapper.countCategoriesByName(categoryName) > 0;
    }


    public boolean isCategoryNameDuplicated(String categoryName, int categoryCode) {
        return categoryMapper.countCategoriesByNameAndCode(categoryName, categoryCode) > 0;
    }

    public boolean isMainCategoryInUse(int categoryCode) {
        return categoryMapper.countSubCategoriesByCode(categoryCode) != 0;
    }
}

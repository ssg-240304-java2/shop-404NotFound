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

    /***
     * 메인 카테고리 반환 서비스 메소드
     * @return
     */
    public List<CategoryDto> getMainCategory() {
        return categoryMapper.getMainCategory();
    }

    /***
     * 메인 카테고리에 따른 서브 카테고리 반환 서비스 메소드
     * @param parentId
     * @return
     */
    public List<CategoryDto> getSubCategoryByParentId(int parentId) {
        return categoryMapper.getByRefCategoryCode(parentId);
    }

    /***
     * 전체 카테고리 반환 서비스 메소드
     * @return
     */
    public List<CategoryDto> selectAllCategories() {
        return categoryMapper.selectAllCategories();
    }

    /***
     * 카테고리 생성 서비스 메소드
     * @param categoryDto
     * @return
     */
    @Transactional
    public boolean addCategory(CategoryDto categoryDto) {
        categoryMapper.insertCategory(categoryDto);
        return true;
    }

    /***
     * 카테고리 정보 변경 메소드
     * @param categoryDto
     * @return
     */
    @Transactional
    public boolean updateCategory(CategoryDto categoryDto) {
        categoryMapper.updateCategory(categoryDto);
        return true;
    }
}

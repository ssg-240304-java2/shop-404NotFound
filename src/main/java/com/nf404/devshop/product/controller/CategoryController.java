package com.nf404.devshop.product.controller;

import com.nf404.devshop.product.model.dto.CategoryDto;
import com.nf404.devshop.product.service.CategoryService;
import com.nf404.devshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    public final CategoryService categoryService;
    public final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping
    public String categoryPage(Model model) {
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "product/category";
    }

    @GetMapping("/subcategories")
    @ResponseBody
    public List<CategoryDto> getSubCategories(@RequestParam("parentId") int parentId) {
        return categoryService.getSubCategoryByParentId(parentId);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addCategory(@RequestParam("categoryName") String categoryName,
                              @RequestParam(value = "refCategoryCode", required = false) Integer refCategoryCode) {

        if (categoryService.isCategoryNameDuplicated(categoryName))
            throw new IllegalArgumentException("이미 존재하는 카테고리 이름입니다!");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        categoryDto.setRefCategoryCode(refCategoryCode);

        categoryService.addCategory(categoryDto);

        return "카테고리 등록 완료!";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String editCategory(@RequestParam("categoryCode") int categoryCode,
                               @RequestParam("categoryName") String categoryName,
                               @RequestParam(value = "refCategoryCode", required = false) Integer refCategoryCode) {

        if (categoryService.isCategoryNameDuplicated(categoryName, categoryCode))
            throw new IllegalArgumentException("이미 존재하는 카테고리 이름입니다!");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        categoryDto.setRefCategoryCode(refCategoryCode);
        categoryDto.setCategoryCode(categoryCode);

        categoryService.updateCategory(categoryDto);

        return "카테고리 수정 완료!";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteCategory(@RequestParam("categoryCode") int categoryCode) {

        if(categoryService.getCategoryByCode(categoryCode).getRefCategoryCode() == null) {
            if(categoryService.isMainCategoryInUse(categoryCode)) {
                throw new IllegalArgumentException("해당 카테고리의 서브 카테고리가 존재하여 삭제할 수 없습니다!");
            }
        } else {
            if(!productService.isCategoryEmpty(categoryCode))
                throw new IllegalArgumentException("해당 카테고리의 상품이 존재하여 삭제할 수 없습니다!");
        }

        categoryService.deleteCategory(categoryCode);
        return "카테고리 삭제 완료!";
    }

}

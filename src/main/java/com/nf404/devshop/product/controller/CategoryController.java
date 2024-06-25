package com.nf404.devshop.product.controller;

import com.nf404.devshop.product.model.dto.CategoryDto;
import com.nf404.devshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    public final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/subcategories")
    @ResponseBody
    public List<CategoryDto> getSubCategories(@RequestParam("parentId") int parentId) {
        return categoryService.getSubCategoryByParentId(parentId);
    }
}

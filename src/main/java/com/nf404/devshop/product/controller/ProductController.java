package com.nf404.devshop.product.controller;

import com.nf404.devshop.product.dto.req.ProductCriteria;
import com.nf404.devshop.product.dto.res.CategoryDto;
import com.nf404.devshop.product.dto.res.ProductReadResDto;
import com.nf404.devshop.product.service.CategoryService;
import com.nf404.devshop.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product-list")
    public String searchProducts(@ModelAttribute ProductCriteria productCriteria, Model model) {

//        log.info("[ProductCriteria] : {}", productCriteria);

        List<ProductReadResDto> productList = productService.getProductInfo(productCriteria);
//        log.info("[ProductList] : {}", productList);
        model.addAttribute("productList", productList);

        List<CategoryDto> mainCategories = categoryService.getMainCategory();
        model.addAttribute("mainCategories", mainCategories);

        return "/product/product-list";
    }

    @GetMapping("/add-product")
    public void addProductPage() {};


//    @PostMapping("/add-product")
//    public String addProduct(@ModelAttribute) {
//
//    }
}

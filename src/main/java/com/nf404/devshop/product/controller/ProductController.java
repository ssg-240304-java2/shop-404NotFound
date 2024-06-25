package com.nf404.devshop.product.controller;

import com.nf404.devshop.product.model.dto.ImageDto;
import com.nf404.devshop.product.model.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.model.dto.req.ProductCriteria;
import com.nf404.devshop.product.model.dto.CategoryDto;
import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
import com.nf404.devshop.product.service.CategoryService;
import com.nf404.devshop.product.service.ProductService;
import com.nf404.devshop.global.utility.SaveImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SaveImageUtil saveImageUtil;
    private final String filePath = "ssg-java2.iptime.org/nf404/product";

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, SaveImageUtil saveImageUtil) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.saveImageUtil = saveImageUtil;
    }

    @GetMapping("/product-list")
    public String searchProducts(@ModelAttribute ProductCriteria productCriteria, Model model) {

        List<ProductReadResDto> productList = productService.getProductInfo(productCriteria);

        for(ProductReadResDto productReadResDto : productList)
            productReadResDto.getImage().setUuidFilename(filePath + productReadResDto.getImage().getUuidFilename());

        model.addAttribute("productList", productList);

        List<CategoryDto> mainCategories = categoryService.getMainCategory();
        model.addAttribute("mainCategories", mainCategories);

        return "/product/product-list";
    }

    @GetMapping("/add-product")
    public String addProductPage(Model model) {
        List<CategoryDto> mainCategories = categoryService.getMainCategory();
        model.addAttribute("mainCategories", mainCategories);

        return "/product/add-product";
    };

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute ProductCreateReqDto productCreateReqDto, @RequestParam("imageFile") MultipartFile imageFile) {

        log.info("[ProductController] productCreateReqDto: {}", productCreateReqDto);
        log.info("[ProductController] imageFile: {}", imageFile);

        ImageDto imageDto = null;

        try {
            imageDto = saveImageUtil.upload(imageFile, "product");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productService.addProductInfo(productCreateReqDto, imageDto);

        return "redirect:/product/add-product";
    }
}

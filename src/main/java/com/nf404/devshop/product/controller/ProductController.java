package com.nf404.devshop.product.controller;

import com.nf404.devshop.product.model.dto.ImageDto;
import com.nf404.devshop.product.model.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.model.dto.req.ProductCriteria;
import com.nf404.devshop.product.model.dto.CategoryDto;
import com.nf404.devshop.product.model.dto.req.ProductUpdateReqDto;
import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
import com.nf404.devshop.product.service.CategoryService;
import com.nf404.devshop.product.service.ProductService;
import com.nf404.devshop.global.utility.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageUtil imageUtil;
    private final String filePath = "http://ssg-java2.iptime.org/nf404/product/";

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ImageUtil imageUtil) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageUtil = imageUtil;
    }

    /***
     * [select]
     * - 필터링 조건을 받아 해당하는 상품의 리스트 반환
     * @param productCriteria
     * @param model
     * @return
     */
    @GetMapping("/product-list")
    public String searchProducts(@ModelAttribute ProductCriteria productCriteria, Model model) {

        List<ProductReadResDto> productList = productService.getProductInfo(productCriteria);

        for(ProductReadResDto productReadResDto : productList) {
            String finalFilename = imageUtil.convertFilenameToUrl(filePath, productReadResDto.getImage().getUuidFilename());
            productReadResDto.getImage().setUuidFilename(finalFilename);
        }

        model.addAttribute("productList", productList);
        List<CategoryDto> mainCategories = categoryService.getMainCategory();
        model.addAttribute("mainCategories", mainCategories);
        return "product/product_list";
    }

    /***
     * [insert]
     * - 입력한 정보로 상품 데이터 추가
     * @param productCreateReqDto
     * @param imageFile
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/register")
    public String addProduct(@ModelAttribute ProductCreateReqDto productCreateReqDto,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        ImageDto imageDto = null;

        try {
            if (!imageFile.isEmpty()) imageDto = imageUtil.upload(imageFile, "product");
            else imageDto = new ImageDto("", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productService.addProductInfo(productCreateReqDto, imageDto);
        redirectAttributes.addFlashAttribute("successMessage", "상품이 성공적으로 등록되었습니다!");
        return "redirect:/product/product-list";
    }

    /***
     * 상품 업데이트 페이지 이동
     * @param productCode
     * @param model
     * @return
     */
    @GetMapping("/update")
    public String updateProductPage(@RequestParam("productCode") Integer productCode, Model model) {

        ProductReadResDto productInfo = productService.getProductInfoByCode(productCode);
        String finalFilename = imageUtil.convertFilenameToUrl(filePath, productInfo.getImage().getUuidFilename());
        productInfo.getImage().setUuidFilename(finalFilename);

        model.addAttribute("productInfo", productInfo);

        List<CategoryDto> mainCategories = categoryService.getMainCategory();
        model.addAttribute("mainCategories", mainCategories);
        return "product/update";
    }


    /***
     * [update]
     * - 선택한 상품의 데이터 업데이트
     * @param productUpdateReqDto
     * @param imageFile
     * @param originalFileUrl
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/update")
    public String updateProductInfo(@ModelAttribute ProductUpdateReqDto productUpdateReqDto,
                                    @RequestParam("imageFile") MultipartFile imageFile,
                                    @RequestParam("uuidFilename") String originalFileUrl,
                                    RedirectAttributes redirectAttributes) {

        ImageDto imageDto = null;
        String filename = imageUtil.convertUrlToFilename(originalFileUrl);
        log.info("[filename] : {}", filename);

        try {
            if (!imageFile.isEmpty()) {
                imageDto = imageUtil.upload(imageFile, "product");
                if(!filename.isEmpty())
                    imageUtil.deleteFile(filename, "product");
            }
            else imageDto = new ImageDto("", "");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageDto.setThumbnailPath(productUpdateReqDto.getThumbnailPath());
        productService.updateProductInfo(productUpdateReqDto, imageDto);

        redirectAttributes.addFlashAttribute("successMessage", "상품 정보가 성공적으로 변경 되었습니다!");
        return "redirect:/product/product-list";
    }

    /***
     * [delete]
     * - 선택된 모든 상품의 판매 상태 값 변경
     * @param productCodeList
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/disable-product")
    public String deleteProduct(@RequestParam("selectedProduct[]") List<Integer> productCodeList,
                                RedirectAttributes redirectAttributes) {

        productService.updateProductStatusInfo(productCodeList);
        redirectAttributes.addFlashAttribute("successMessage", "상품의 판매상태가 성공적으로 변경 되었습니다!");
        return "redirect:/product/product-list";
    }
}

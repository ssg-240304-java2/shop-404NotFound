package com.nf404.devshop.product.service;

import com.nf404.devshop.product.dto.req.*;
import com.nf404.devshop.product.dto.res.ProductReadResDto;
import com.nf404.devshop.product.repository.ImageMapper;
import com.nf404.devshop.product.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;
    // 재고 mapper

    public ProductService(ProductMapper productMapper, ImageMapper imageMapper) {
        this.productMapper = productMapper;
        this.imageMapper = imageMapper;
        // 재고 mapper 생성자 주입
    }

    /***
     * 상품 필터링해서 조회 서비스 메소드 (전체 조회 포함)
     * @param productCriteria
     * @return
     */
    public List<ProductReadResDto> getProductInfo(ProductCriteria productCriteria) {
        return productMapper.getProductInfoByCriteria(productCriteria);
    }

    /***
     * 신상품 추가 서비스 메소드
     * @param productCreateReqDto
     * @param imageDto
     * @return
     */
    @Transactional
    public boolean addProductInfo(ProductCreateReqDto productCreateReqDto, ImageDto imageDto) {

        imageMapper.insertImageInfo(imageDto);

        productCreateReqDto.setThumbnailPath(imageDto.getThumbnailPath());
        productMapper.insertProductInfo(productCreateReqDto);
        // 3. 재고 테이블 생성
        // 이 부분 채워야 한다.
//        int stockQuantity = productCreateReqDto.getStockQuantity(); // 초기 재고 수량 넣을 값
//        int productCode = productCreateReqDto.getProductCode(); // 재고 코드 값 , 작업 1
        return true;
    }

    /***
     * 상품 정보 변경 서비스 메소드
     * @param productUpadateReqDto
     * @param imageDto
     * @return
     */
    @Transactional
    public boolean updateProductInfo(ProductUpadateReqDto productUpadateReqDto, ImageDto imageDto) {
        productMapper.updateProductInfo(productUpadateReqDto);
        imageMapper.updateImageInfo(imageDto, productUpadateReqDto.getProductCode());
        return true;
    }

    /***
     * 상품 정보 비활성화 서비스 메소드
     * @param productDeleteReqDto
     * @return
     */
    @Transactional
    public boolean deleteProductInfo(ProductDeleteReqDto productDeleteReqDto) {
        productMapper.deleteProductInfo(productDeleteReqDto);
        return true;
    }
}

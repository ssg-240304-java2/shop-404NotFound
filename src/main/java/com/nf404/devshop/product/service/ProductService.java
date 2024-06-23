package com.nf404.devshop.product.service;

import com.nf404.devshop.product.domain.Image;
import com.nf404.devshop.product.domain.Product;
import com.nf404.devshop.product.dto.req.ImageCreateReqDto;
import com.nf404.devshop.product.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.dto.req.ProductCriteria;
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

    public List<ProductReadResDto> getProductInfo(ProductCriteria productCriteria) {
        return productMapper.getProductInfoByCriteria(productCriteria);
    }

    @Transactional
    public boolean addProductInfo(ProductCreateReqDto productCreateReqDto, ImageCreateReqDto imageCreateReqDto) {

        Image image = convertImageDtoToEntity(imageCreateReqDto);
        Product product = convertProductDtoToEntity(productCreateReqDto);

        // 1. 이미지 생성 후 이미지 번호 반환
        imageMapper.insertImageInfo(image);
        // 2. 상품 생성 후 상품 번호 반환
        product.setThumbnailPath(image.getThumbnailPath());
        productMapper.insertProductInfo(product);
        // 3. 재고 테이블 생성
        // 이 부분 채워야 한다.
        int stockQuantity = productCreateReqDto.getStockQuantity(); // 초기 재고 수량 넣을 값
        int productCode = product.getProductCode(); // 재고 코드 값 , 작업 1

        return true;
    }

    private Product convertProductDtoToEntity(ProductCreateReqDto productCreateReqDto) {
        Product product = new Product();
        product.setProductName(productCreateReqDto.getProductName());
        product.setPrice(productCreateReqDto.getPrice());
        product.setIsDisplayed(productCreateReqDto.getIsDisplayed());
        product.setProductDesc(productCreateReqDto.getProductDesc());
        return product;
    }

    private Image convertImageDtoToEntity(ImageCreateReqDto imageCreateReqDto) {
        Image image = new Image();
        image.setOriginFilename(imageCreateReqDto.getOriginFilename());
        image.setUuidFilename(imageCreateReqDto.getUuidFilename());
        return image;
    }
}

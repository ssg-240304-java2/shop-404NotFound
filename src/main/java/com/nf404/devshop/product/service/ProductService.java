package com.nf404.devshop.product.service;

import com.nf404.devshop.inventory.stock.StockMapper;
import com.nf404.devshop.product.model.dto.ImageDto;
import com.nf404.devshop.product.model.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.model.dto.req.ProductCriteria;
import com.nf404.devshop.product.model.dto.req.ProductUpdateReqDto;
import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
import com.nf404.devshop.product.repository.ImageMapper;
import com.nf404.devshop.product.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;
    private final StockMapper stockMapper;

    public ProductService(ProductMapper productMapper, ImageMapper imageMapper, StockMapper stockMapper) {
        this.productMapper = productMapper;
        this.imageMapper = imageMapper;
        this.stockMapper = stockMapper;
    }

    /***
     * 상품 필터링해서 조회 서비스 메소드 (전체 조회 포함)
     * productCriteria의 값들이 모두 null이면 전체 출력을 한다.
     * @param productCriteria
     * @return
     */
    public List<ProductReadResDto> getProductInfo(ProductCriteria productCriteria) {
        return productMapper.selectProductInfoByCriteria(productCriteria);
    }

    public ProductReadResDto getProductInfoByCode(int productCode) {
        return productMapper.selectProductInfoByCode(productCode);
    }

    // =========================================================================================================

    /***
     * 신상품 추가 서비스 메소드
     * @param productCreateReqDto
     * @param imageDto
     * @return
     */
    @Transactional
    public void addProductInfo(ProductCreateReqDto productCreateReqDto, ImageDto imageDto) {

        imageMapper.insertImageInfo(imageDto);
        productCreateReqDto.setThumbnailPath(imageDto.getThumbnailPath());
        productMapper.insertProductInfo(productCreateReqDto);
        stockMapper.insertNewStockTuple(productCreateReqDto.getProductCode());
    }

    /***
     * 상품 정보 변경 서비스 메소드
     * @param productUpdateReqDto
     * @param imageDto
     * @return
     */
    @Transactional
    public void updateProductInfo(ProductUpdateReqDto productUpdateReqDto, ImageDto imageDto) {
        productMapper.updateProductInfo(productUpdateReqDto);

        if(!Objects.equals(imageDto.getUuidFilename(), ""))
            imageMapper.updateImageInfo(imageDto);
    }

    /***
     * 상품 판매 상태 (판매 여부) 일괄 변경 메소드
     * @param productCodeList
     */
    @Transactional
    public void updateProductStatusInfo(List<Integer> productCodeList) {
        productMapper.updateProductStatusInfo(productCodeList);
    }
}

package com.nf404.devshop.product.service;

import com.nf404.devshop.product.model.dto.ImageDto;
import com.nf404.devshop.product.model.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.model.dto.req.ProductCriteria;
import com.nf404.devshop.product.model.dto.req.ProductDeleteReqDto;
import com.nf404.devshop.product.model.dto.req.ProductUpadateReqDto;
import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
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
        // 여기서 재고테이블 dto에 입력한 초기 재고수량을 담은 dto를 인자로 받아야 한다.
        imageMapper.insertImageInfo(imageDto);

        productCreateReqDto.setThumbnailPath(imageDto.getThumbnailPath());
        productMapper.insertProductInfo(productCreateReqDto);
//         3. 재고 테이블 생성
//         이 부분 채워야 한다.
//        int stockQuantity = productCreateReqDto.getStockQuantity(); // 초기 재고 수량 넣을 값
//        int productCode = productCreateReqDto.getProductCode(); // 재고 코드 값 , 작업
    }


    /***
     * 상품 정보 변경 서비스 메소드
     * @param productUpadateReqDto
     * @param imageDto
     * @return
     */
    @Transactional
    public void updateProductInfo(ProductUpadateReqDto productUpadateReqDto, ImageDto imageDto) {
        productMapper.updateProductInfo(productUpadateReqDto);
        imageDto.setThumbnailPath(productUpadateReqDto.getThumbnailPath());
        imageMapper.updateImageInfo(imageDto);
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

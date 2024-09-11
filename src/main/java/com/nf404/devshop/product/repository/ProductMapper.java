package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.model.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.model.dto.req.ProductCriteria;
import com.nf404.devshop.product.model.dto.req.ProductUpdateReqDto;
import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    
    List<ProductReadResDto> selectProductInfoByCriteria(ProductCriteria productCriteria);

    ProductReadResDto selectProductInfoByCode(int productCode);

    void insertProductInfo(ProductCreateReqDto productCreateReqDto);

    void updateProductInfo(ProductUpdateReqDto productUpdateReqDto);

    void updateProductStatusInfo(List<Integer> productCodeList);

    int countProductsByCategoryCode(int categoryCode);
}

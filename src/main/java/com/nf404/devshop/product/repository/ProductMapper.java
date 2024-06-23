package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.dto.req.ProductCreateReqDto;
import com.nf404.devshop.product.dto.req.ProductCriteria;
import com.nf404.devshop.product.dto.req.ProductDeleteReqDto;
import com.nf404.devshop.product.dto.req.ProductUpadateReqDto;
import com.nf404.devshop.product.dto.res.ProductReadResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductReadResDto> getAllProductInfo();

    List<ProductReadResDto> getProductInfoByCriteria(ProductCriteria productCriteria);

    void insertProductInfo(ProductCreateReqDto productCreateReqDto);

    void updateProductInfo(ProductUpadateReqDto productUpadateReqDto);

    void deleteProductInfo(ProductDeleteReqDto productDeleteReqDto);
}

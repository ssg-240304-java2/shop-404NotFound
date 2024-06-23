package com.nf404.devshop.product.service;

import com.nf404.devshop.product.dto.req.ProductCriteria;
import com.nf404.devshop.product.dto.res.ProductReadResDto;
import com.nf404.devshop.product.repository.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<ProductReadResDto> getProductInfo(ProductCriteria productCriteria) {
        return productMapper.getProductInfoByCriteria(productCriteria);
    }
}

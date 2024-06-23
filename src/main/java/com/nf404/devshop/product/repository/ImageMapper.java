package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.dto.req.ImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {

    void insertImageInfo(ImageDto imageDto);

    void updateImageInfo(@Param("image") ImageDto imageDto, @Param("productCode") int productCode);
}

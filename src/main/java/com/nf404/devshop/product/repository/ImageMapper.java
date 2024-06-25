package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.model.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    void insertImageInfo(ImageDto imageDto);

    void updateImageInfo(ImageDto imageDto);
}

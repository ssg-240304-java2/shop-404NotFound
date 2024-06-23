package com.nf404.devshop.product.repository;

import com.nf404.devshop.product.domain.Image;
import com.nf404.devshop.product.dto.req.ImageCreateReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    int insertImageInfo(Image image);
}

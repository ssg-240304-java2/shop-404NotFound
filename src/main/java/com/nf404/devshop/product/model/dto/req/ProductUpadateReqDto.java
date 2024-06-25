package com.nf404.devshop.product.model.dto.req;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductUpadateReqDto {

    private int productCode;
    private String productName;
    private int price;
    private int categoryCode; // subCategoryCode 로 바꿔야할지 모른다.
    private String isDisplayed;
    private String productDesc;
    private int thumbnailPath;
}

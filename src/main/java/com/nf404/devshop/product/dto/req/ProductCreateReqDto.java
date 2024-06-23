package com.nf404.devshop.product.dto.req;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductCreateReqDto {

    private String productName;
    private int price;
    private int categoryCode;
    private String isDisplayed;
    private String productDesc;
    private int thumbnailPath;
    private int stockQuantity;
}

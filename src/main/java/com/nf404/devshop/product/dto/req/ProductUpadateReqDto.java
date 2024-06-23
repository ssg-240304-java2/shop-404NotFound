package com.nf404.devshop.product.dto.req;

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
    private int categoryCode;
    private String isDisplayed;
    private String productDesc;
}

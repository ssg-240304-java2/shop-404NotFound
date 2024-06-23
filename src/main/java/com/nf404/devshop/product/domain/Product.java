package com.nf404.devshop.product.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private int productCode;
    private String productName;
    private int price;
    private int categoryCode;
    private String isDisplayed;
    private String productDesc;
    private int thumbnailPath;
    private LocalDateTime registrationDate;
}

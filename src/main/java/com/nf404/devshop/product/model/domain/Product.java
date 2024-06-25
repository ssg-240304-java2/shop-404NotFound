package com.nf404.devshop.product.model.domain;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Product {

    private int productCode;
    private String productName;
    private int price;
    private int categoryCode;
    private String isDisplayed;
    private String productDesc;
    private int thumbnailPath;
    private LocalDate registrationDate;

}

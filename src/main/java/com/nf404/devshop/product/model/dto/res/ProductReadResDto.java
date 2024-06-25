package com.nf404.devshop.product.model.dto.res;

import com.nf404.devshop.product.model.domain.Category;
import com.nf404.devshop.product.model.domain.Image;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductReadResDto {

    private int productCode;
    private String productName;
    private int price;
    private String isDisplayed;
    private String productDesc;
    private LocalDate registrationDate;

    private Category category;
    private Image image;
}


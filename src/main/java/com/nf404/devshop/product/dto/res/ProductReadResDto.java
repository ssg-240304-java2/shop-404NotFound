package com.nf404.devshop.product.dto.res;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductReadResDto {

    private int productCode;
    private String productName;
    private int price;
    private String categoryName;
    private String isDisplayed;
    private String productDesc;
    private String uuidFilename;
    private LocalDate registrationDate;
}

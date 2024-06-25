package com.nf404.devshop.product.model.dto.req;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductCriteria {

    private String productName;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer mainCategoryCode;
    private Integer subCategoryCode;
    private String isDisplayed;
    private LocalDate startDate;
    private LocalDate endDate;
}

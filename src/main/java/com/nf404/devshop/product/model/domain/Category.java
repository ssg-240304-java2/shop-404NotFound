package com.nf404.devshop.product.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    private int categoryCode;
    private String categoryName;
    private int refCategoryCode;
}

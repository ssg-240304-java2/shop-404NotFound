package com.nf404.devshop.inventory.stock;

import com.nf404.devshop.product.model.dto.res.ProductReadResDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockAndProduct {
    private int productCode;
    private int stockQuantity;

    // 상품코드, 상품명, 재고수량
    private ProductReadResDto product;
}

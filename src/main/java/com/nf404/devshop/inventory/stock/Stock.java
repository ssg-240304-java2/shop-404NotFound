package com.nf404.devshop.inventory.stock;

import lombok.*;

/**
 * tbl_inventory_stock
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Stock {
    // product_code(PK, FK) INT : 상품코드
    // stock_quantity INT : 재고수량
    private int productCode;
    private int stockQuantity;
}

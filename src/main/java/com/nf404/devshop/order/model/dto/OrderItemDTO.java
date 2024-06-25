package com.nf404.devshop.order.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemDTO {
    private int orderNo;
    private int userNo;
    private int productCode;
    private int orderItemQuantity;
    private String productName;
    private int Price;

}

package com.nf404.devshop.order.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderSummaryDTO {
    private int userNo;
    private String userId;
    private String userName;
    private int allOrderCount;
    private int allOrderPrice;
}

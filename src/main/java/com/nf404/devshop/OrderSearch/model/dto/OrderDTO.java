package com.nf404.devshop.OrderSearch.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private int orderNo;
    private int userNo;
    private int orderTotalPrice;
    private LocalDateTime createdAt;
    private String orderStatus;
    private List<UserDTO> userInfo;
    private List<OrderItemDTO> orderItems;
}

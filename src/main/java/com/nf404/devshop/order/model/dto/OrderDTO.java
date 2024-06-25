package com.nf404.devshop.order.model.dto;

import lombok.*;
import com.nf404.devshop.user.model.UserDTO;
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
    private UserDTO userInfo;
    private List<OrderItemDTO> orderItems;
}

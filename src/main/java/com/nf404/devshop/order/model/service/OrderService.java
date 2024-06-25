package com.nf404.devshop.order.model.service;

import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrder();


    List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo);
}

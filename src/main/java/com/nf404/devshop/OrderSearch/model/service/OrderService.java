package com.nf404.devshop.OrderSearch.model.service;

import com.nf404.devshop.OrderSearch.model.dto.OrderDTO;
import com.nf404.devshop.OrderSearch.model.dto.OrderItemDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrder();


    List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo);
}

package com.nf404.devshop.order.model.service;

import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import com.nf404.devshop.order.model.dto.OrderSummaryDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrder();


    List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo);



    List<OrderDTO> searchAndFilterOrders(String search, String startDate, String endDate);


    List<OrderSummaryDTO> getOrderSummaryByUser();

}

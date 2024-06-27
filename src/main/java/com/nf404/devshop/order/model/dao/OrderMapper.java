package com.nf404.devshop.order.model.dao;

import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import com.nf404.devshop.order.model.dto.OrderSummaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<OrderDTO> findAllOrder();


    List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo);

    List<OrderItemDTO> findOrderItemsByOrderNo(int orderNo);


    List<OrderDTO> searchAndFilterOrders(Map<String, Object> map);


    List<OrderSummaryDTO> getOrderSummaryByUser();

}

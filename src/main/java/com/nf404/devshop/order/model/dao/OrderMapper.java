package com.nf404.devshop.order.model.dao;

import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderDTO> findAllOrder();


    List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo);
}
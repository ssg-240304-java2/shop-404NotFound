package com.nf404.devshop.order.model.service;

import com.nf404.devshop.order.model.dao.OrderMapper;
import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> findAllOrder() {
        return orderMapper.findAllOrder();
    }

    @Override
    public List<OrderItemDTO> findOrderDetailByOrderNo(int orderNo) {
        return orderMapper.findOrderDetailByOrderNo(orderNo);
    }

    @Override
    public List<OrderDTO> searchAndFilterOrders(String search, String startDate, String endDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        map.put("startDate",  startDate);
        map.put("endDate", endDate);
        return orderMapper.searchAndFilterOrders(map);
    }


//    @Override
//    public List<OrderDTO> searchOrders(String search) {
//        return orderMapper.searchOrders(search);
//    }


}

package com.nf404.devshop.order.model.service;

import com.nf404.devshop.inventory.InventoryService;
import com.nf404.devshop.order.model.dao.OrderMapper;
import com.nf404.devshop.order.model.dao.UpdateStatusMapper;
import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdateStatusImpl implements UpdateStatusService {
    private final UpdateStatusMapper updateStatusMapper;
    private final InventoryService inventoryService;
    private final OrderMapper orderMapper;

    @Autowired
    public UpdateStatusImpl(UpdateStatusMapper updateStatusMapper, InventoryService inventoryService, OrderMapper orderMapper) {
        this.updateStatusMapper = updateStatusMapper;
        this.inventoryService = inventoryService;
        this.orderMapper = orderMapper;
    }


    @Transactional
    @Override
    public void updateOrderStatus(int orderNo, String orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("orderStatus", orderStatus);
        updateStatusMapper.updateOrderStatus(map);
        if ("처리완료".equals(orderStatus)) {
            List<OrderItemDTO> orderItems = orderMapper.findOrderDetailByOrderNo(orderNo);
            for (OrderItemDTO orderItem : orderItems) {
                inventoryService.updateStockOutbound(orderItem.getProductCode(), orderItem.getOrderItemQuantity());
            }
        }
    }
}

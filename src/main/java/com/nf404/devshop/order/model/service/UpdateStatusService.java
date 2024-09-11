package com.nf404.devshop.order.model.service;

import com.nf404.devshop.order.model.dto.OrderDTO;

public interface UpdateStatusService {


    void updateOrderStatus(int orderNo, String orderStatus);
}

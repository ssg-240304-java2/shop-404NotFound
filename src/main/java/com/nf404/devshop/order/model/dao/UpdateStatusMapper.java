package com.nf404.devshop.order.model.dao;

import com.nf404.devshop.order.model.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UpdateStatusMapper {

    void updateOrderStatus(Map<String, Object> map);
}

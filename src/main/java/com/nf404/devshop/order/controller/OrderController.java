package com.nf404.devshop.order.controller;

import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import com.nf404.devshop.order.model.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /***
     * 전체 주문 리스트
     * @param model
     * @return
     */
    @GetMapping("/orderlist1")
    public String allOrderList(@RequestParam(value = "search",required = false) String search,Model model){
        log.info(">>>> 검색어 >>>>>>>>> {}", search);
        List<OrderDTO> orderList;
        if (search != null && !search.isEmpty()) {
            orderList = orderService.searchOrders(search);
        } else {
            orderList = orderService.findAllOrder();
        }
        model.addAttribute("orderList", orderList);

        return "order/orderlist1";
    }

    /***
     * 전체 주문 조회에서 주문 번호를 눌렀을 때 주문 상세 정보
     * @param orderNo
     * @param model
     * @return
     */
    @GetMapping("/detail/{orderNo}")
    public String orderDetail(@PathVariable int orderNo, Model model){
        log.info("주문번호>>>>>>>>>>>>>>>>>>> {}", orderNo);
        List<OrderItemDTO> orderDetail = orderService.findOrderDetailByOrderNo(orderNo);
        log.info("[주문 번호 상세 조회]>>>>>> {}", orderDetail);
        model.addAttribute("orderDetail", orderDetail);
        return "order/orderdetail";
    }


}

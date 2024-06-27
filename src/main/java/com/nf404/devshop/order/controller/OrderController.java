package com.nf404.devshop.order.controller;

import com.nf404.devshop.order.common.paging.Pagenation;
import com.nf404.devshop.order.common.paging.SelectCriteria;
import com.nf404.devshop.order.model.dto.OrderDTO;
import com.nf404.devshop.order.model.dto.OrderItemDTO;
import com.nf404.devshop.order.model.dto.OrderSummaryDTO;
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
     * 전체 주문 조회 기능 & 필터링
     * @param search
     * @param startDate
     * @param endDate
     * @param model
     * @return
     */
    @GetMapping("/orderlist")
    public String allOrderList(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            Model model) {
        // 만약 시작 일자 입력 x -> null 값 채워주기
        if (startDate == null || startDate.isEmpty()) {
            startDate = "1999-01-01";
        }
        if (endDate == null || endDate.isEmpty()) {
            endDate = "2999-12-31";
        }
        List<OrderDTO> orderList;
        if ((search != null && !search.isEmpty()) || (startDate != null && endDate != null)) {
            orderList = orderService.searchAndFilterOrders(search, startDate, endDate);
        } else {
            orderList = orderService.findAllOrder();
        }

        model.addAttribute("orderList", orderList);
        return "order/orderlist";
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
        return "/order/orderdetail";
    }

    /***
     * 사용자 별 결제 총 금액과 주문 횟수
     * @param model
     * @return
     */
    @GetMapping("/ordersummarylist")
    public String orderSummary(Model model) {
        List<OrderSummaryDTO> orderSummaryList = orderService.getOrderSummaryByUser();
//        log.info(">>>>>>>>>>>>>>>>>>>> Order Summary: {}", orderSummaryList);
        model.addAttribute("orderSummaryList", orderSummaryList);
        return "order/ordersummarylist";
    }
}

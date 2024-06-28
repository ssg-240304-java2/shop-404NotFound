package com.nf404.devshop.order.controller;

import com.nf404.devshop.order.model.service.UpdateStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/order")
public class UpdateStatusController {
    private final UpdateStatusService updateStatusService;

    public UpdateStatusController(UpdateStatusService updateStatusService) {
        this.updateStatusService = updateStatusService;
    }

    /***
     * 주문 상태 변경
     * @param orderNo
     * @param orderStatus
     */
    @PostMapping("/updatestatus")
    public String updateOrderStatus(@RequestParam(required = false) int orderNo, @RequestParam(required = false) String orderStatus, RedirectAttributes redirectAttributes) {
        log.info("orderNo >>>>>>>>>>>>> {}", orderNo);
        log.info("orderStatus >>>>>>>> {}", orderStatus);

        updateStatusService.updateOrderStatus(orderNo, orderStatus);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> 주문 상태 처리 완료");
        redirectAttributes.addFlashAttribute("updateSuccess", true);
        return "redirect:/order/orderlist";
    }
}

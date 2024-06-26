package com.nf404.devshop.inventory;

import com.nf404.devshop.inventory.stock.StockAndProduct;
import com.nf404.devshop.inventory.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/stock")
public class StockController {
    private final InventoryService inventoryService;

    @Autowired
    public StockController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<StockAndProduct> allStocksInfoWithProductInfo = inventoryService.getAllStocksInfoWithProductInfo();

        model.addAttribute("itemList", allStocksInfoWithProductInfo);
        return "inventory/inbound";
    }

    // use pathvalue
    // admin/stock/list/1 ...
    @GetMapping("/list/{productCode}")
    public String listByProductCode(@PathVariable int productCode, Model model) {
        System.out.println("productCode = " + productCode);

        model.addAttribute("productCode", productCode);
        return "inventory/inbound_process";
    }

    @PostMapping("/inbound")
    public String inbound(@RequestParam int productCode, @RequestParam int quantity) {
        inventoryService.updateStockInbound(productCode, quantity);
        return "redirect:/admin/stock/list";
    }


    @GetMapping("/logs")
    public String listLogs(Model model) {
        /*
                // history_code(PK) INT : 이력 코드
            // executed_at TIMESTAMP : 수행 일시
            // operated_quantity INT : 처리 수량
            // task_id(FK) INT : 작업 코드
            // product_code(FK) INT : 상품 코드
            private int historyCode;
            private Timestamp executedAt;
            private int operatedQuantity;
        //    private int taskId;

            // use Association
            private Task task;

            private int productCode;
         */
        /*
        @NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Task {
    // task_id(PK) INT : 작업 코드
    // task_name VARCHAR(30) : 작업명
    private int taskId;
    private String taskName;
}
         */
        List<Transactions> allTransactionLogs = inventoryService.getAllTransactionLogs();
        model.addAttribute("itemList", allTransactionLogs);
        return "inventory/inven_transaction";
    }
}

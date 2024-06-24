package com.nf404.devshop.inventory;

import com.nf404.devshop.inventory.stock.Stock;
import com.nf404.devshop.inventory.transactions.Transactions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class InventoryServiceTest {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryServiceTest(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Test
    @DisplayName("전체 재고 조회")
    void getAllStocksAsList() {
        List<Stock> stocks = inventoryService.getAllStocksAsList();
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    @Test
    @DisplayName("상품별 재고 조회")
    void getStockByProductId() {
        int productCode = 1;

        Stock stock = inventoryService.getStockByProductId(productCode);
        System.out.println(stock.getStockQuantity());
    }

    @Test
    @DisplayName("상품별 재고 추가")
    void updateStockInbound() {
        // 전체 재고 가져오기
        List<Stock> stocks = inventoryService.getAllStocksAsList();

        // 재고 추가
        // 각각 0~200 사이의 랜덤값으로 변경
        for (Stock stock : stocks) {
            int productCode = stock.getProductCode();
            int quantity = (int) (Math.random() * 200);
            inventoryService.updateStockInbound(productCode, quantity);
        }
    }

    @Test
    @DisplayName("상품별 재고 추가. 숫자 맞는지 검증 추가")
    void updateStockInboundAndAssert() {
        int productCode = 1;    // 1번 상품으로 테스트
        int quantity = 100;     // 100개 입고

        // 입고 전 재고 확인
        Stock stockBefore = inventoryService.getStockByProductId(productCode);
        System.out.println("Before: " + stockBefore.getStockQuantity() + "개");

        // 입고
        inventoryService.updateStockInbound(productCode, quantity);

        // 입고 후 재고 확인
        Stock stockAfter = inventoryService.getStockByProductId(productCode);
        System.out.println("After: " + stockAfter.getStockQuantity() + "개");

        // 입고 전후 재고 비교
        int expected = stockBefore.getStockQuantity() + quantity;   // 기댓값: 입고 전 재고 + 입고 수량
        System.out.println("Expected: " + expected + "개");
        Assertions.assertEquals(expected, stockAfter.getStockQuantity());

    }

    @Test
    @DisplayName("1개 상품 재고 출고")
    void updateStockOutbound() {
        int productCode = 1;
        int quantity = 200;

        inventoryService.updateStockOutbound(productCode, quantity);

        Stock stock = inventoryService.getStockByProductId(productCode);
        System.out.println(stock.getStockQuantity());
    }

    @Test
    @DisplayName("전체 입출고 트랜잭션 로그 조회")
    void getAllTransactionLogs() {
        List<Transactions> transactions = inventoryService.getAllTransactionLogs();
        for (Transactions transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
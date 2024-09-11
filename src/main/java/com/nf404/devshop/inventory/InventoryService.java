package com.nf404.devshop.inventory;

import com.nf404.devshop.inventory.stock.*;
import com.nf404.devshop.inventory.transactions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final StockMapper stockMapper;
    private final InventoryTransactionMapper inventoryTransactionMapper;

    @Autowired
    public InventoryService(StockMapper stockMapper, InventoryTransactionMapper inventoryTransactionMapper) {
        this.stockMapper = stockMapper;
        this.inventoryTransactionMapper = inventoryTransactionMapper;
    }

    /**
     * 전체 재고 조회
     */
    public List<Stock> getAllStocksAsList() {
        return stockMapper.selectAllStocks();
    }

    /**
     * 상품 코드로 재고 조회
     * #18
     * @param productCode 상품 코드
     * @return Stock 상품 코드에 대한 재고
     * @see StockMapper#selectStockByProductCode(int)
     */
    public Stock getStockByProductId(int productCode) {
        return stockMapper.selectStockByProductCode(productCode);
    }

    /**
     * 입고 로직
     * #19
     * 재고 테이블에 입고 수량을 업데이트하고 입고 트랜잭션 로그를 추가한다.
     * @param productCode 상품 코드
     * @param quantity    입고 수량
     * @see StockMapper#updateStockInbound(int, int)
     * @see InventoryTransactionMapper#insertInboundTransactionLog(int, int, int)
     */
    @Transactional
    public void updateStockInbound(int productCode, int quantity) {
        // 1. 현재 보유 재고량 가져오기
        Stock stock = stockMapper.selectStockByProductCode(productCode);

        // 2. 재고 테이블 업데이트
        stockMapper.updateStockInbound(productCode, stock.getStockQuantity() + quantity);

        // 3. 입고 트랜잭션 로그 추가
        inventoryTransactionMapper.insertInboundTransactionLog(productCode, quantity, 1);
    }

    /**
     * 출고 로직
     * #19
     * 재고 테이블에 출고 수량을 업데이트하고 출고 트랜잭션 로그를 추가한다.
     * @param productCode 상품 코드
     * @param quantity    출고 수량
     * @see StockMapper#updateStockOutbound(int, int)
     * @see InventoryTransactionMapper#insertOutboundTransactionLog(int, int, int)
     * @throws IllegalArgumentException 출고 수량이 보유 재고량보다 많은 경우 예외 발생
     */
    @Transactional
    public void updateStockOutbound(int productCode, int quantity) {
        // 1. 현재 보유 재고량 가져오기
        Stock stock = stockMapper.selectStockByProductCode(productCode);

        // 2. 출고 수량이 보유 재고량보다 많은 경우 예외 처리
        if (stock.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("출고 수량이 보유 재고량보다 많습니다.");
            // @Transactional이 있기 때문에 롤백 처리됨 -> 발생된 예외는 외부 컨트롤러에서 처리해야하나?
        }

        // 3. 재고 테이블 업데이트
        stockMapper.updateStockOutbound(productCode, stock.getStockQuantity() - quantity);

        // 4. 출고 트랜잭션 로그 추가
        inventoryTransactionMapper.insertOutboundTransactionLog(productCode, quantity, 2);
    }

    // 입출고 이력 전체 조회
    public List<Transactions> getAllTransactionLogs() {
        return inventoryTransactionMapper.selectAllTransactionLogs();
    }

    @Transactional
    public void insertNewStockTuple(int productCode) {
        stockMapper.insertNewStockTuple(productCode);
    }

    /**
     * 모든 재고수량과 상품정보를 포괄한 데이터를 조회한다.
     * @return List<StockAndProduct> 재고수량과 상품정보를 포함한 리스트
     */
    public List<StockAndProduct> getAllStocksInfoWithProductInfo() {
        return stockMapper.getAllStocksInfoWithProductInfo();
    }
}

package com.nf404.devshop.inventory.stock;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * #18
 */
@Mapper
public interface StockMapper {
    /**
     * 상품 코드로 재고 수량 조회
     * @param productCode 상품 코드
     * @return 재고 수량
     */
    Stock selectStockByProductCode(int productCode);

//    /**
//     * 전체 재고 조회
//     * @return List<Stock> 전체 재고 목록
//     */
//    List<Stock> selectAllStocks();

    /**
     * 입고 로직(update)
     * @param productCode 상품 코드
     * @param quantity 입고 수량
     * @return int 업데이트된 재고 수량
     */
    int updateStockInbound(int productCode, int quantity);

    /**
     * 출고 로직(update)
     * @param productCode 상품 코드
     * @param quantity 출고 수량
     * @return int 업데이트된 재고 수량
     */
    int updateStockOutbound(int productCode, int quantity);

    /**
     * 전체 재고 조회
     * @return List<Stock> 전체 재고 목록
     */
    List<Stock> selectAllStocks();

    /**
     * 재고 테이블에 새로운 상품 코드에 대한 재고 튜플을 추가한다. <br>
     * 초깃값은 0으로 설정한다.
     * @param productCode 상품 코드
     */
    void insertNewStockTuple(int productCode);
}

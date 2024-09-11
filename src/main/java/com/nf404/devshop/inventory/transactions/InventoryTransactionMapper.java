package com.nf404.devshop.inventory.transactions;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryTransactionMapper {
    /**
     * 전체 입출고 트랜잭션 로그 조회
     * @return List<Transactions> 전체 입출고 트랜잭션 로그
     */
    List<Transactions> selectAllTransactionLogs();

    /**
     * 특정 기간 범위(시작, 끝) 내의 입출고 트랜잭션 로그 조회
     * 범위 검사를 위한 데이터베이스 내의 칼럼값은 timestamp 형식으로 저장되어 있음
     * @param start 시작 날짜
     * @param end 끝 날짜
     * @return List<Transactions> 특정 기간 범위 내의 입출고 트랜잭션 로그
     */
    List<Transactions> selectTransactionLogsByPeriod(String start, String end);

    /**
     * 특정 상품에 대한 입출고 트랜잭션 로그 조회
     * @param productCode 상품 코드
     * @return List<Transactions> 특정 상품에 대한 입출고 트랜잭션 로그
     */
    List<Transactions> selectTransactionLogsByProduct(int productCode);

    /**
     * 입고 트랜잭션 로그 추가
     * @param productCode 상품 코드
     * @param operatedQuantity 처리 수량
     * @param taskId 작업 코드
     * @return int 추가된 입고 트랜잭션 로그의 수
     */
    int insertInboundTransactionLog(int productCode, int operatedQuantity, int taskId);

    /**
     * 출고 트랜잭션 로그 추가
     * @param productCode 상품 코드
     * @param operatedQuantity 처리 수량
     * @param taskId 작업 코드
     * @return int 추가된 출고 트랜잭션 로그의 수
     */
    int insertOutboundTransactionLog(int productCode, int operatedQuantity, int taskId);
}

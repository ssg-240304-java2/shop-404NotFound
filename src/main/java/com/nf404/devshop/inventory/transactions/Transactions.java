package com.nf404.devshop.inventory.transactions;

import lombok.*;

import java.sql.Timestamp;

/**
 * tbl_inventory_transactions
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Transactions {
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

}

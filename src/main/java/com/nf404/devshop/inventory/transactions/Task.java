package com.nf404.devshop.inventory.transactions;

import lombok.*;

/**
 * tbl_inventory_tasks
 */
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

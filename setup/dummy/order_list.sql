use devshop;
-- 트랜잭션 시작
START TRANSACTION;

-- 1. tbl_order에 새로운 주문 삽입
INSERT INTO tbl_order (user_no, order_totalprice, created_at, order_status)
VALUES (2, 0, CURRENT_TIMESTAMP, '주문접수');

-- 삽입된 주문의 주문 번호 가져오기
SET @last_order_no = LAST_INSERT_ID();

-- 2. tbl_orderitem에 주문 등록
INSERT INTO tbl_orderitem (order_no, product_code, order_item_quantity)
VALUES (@last_order_no, 1, 2),
       (@last_order_no, 3, 1);

-- 3. 주문 총 금액 계산 및 업데이트
UPDATE tbl_order o
    JOIN (
    SELECT oi.order_no, SUM(oi.order_item_quantity * p.price) AS total_amount
    FROM tbl_orderitem oi
    JOIN tbl_product p ON oi.product_code = p.product_code
    WHERE oi.order_no = @last_order_no
    GROUP BY oi.order_no
    ) AS subquery
ON o.order_no = subquery.order_no
    SET o.order_totalprice = subquery.total_amount;

-- 트랜잭션 커밋
COMMIT;

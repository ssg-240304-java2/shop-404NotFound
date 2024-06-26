INSERT INTO tbl_order (user_no, order_totalprice, created_at)
VALUES
    (10, 0, now()),
    (12, 0, now());

INSERT INTO tbl_orderitem (order_no, product_code, order_item_quantity)
VALUES
    (9, 3, 20),
    (9, 5, 11),
    (9, 12, 4),
    (10, 1, 6);

UPDATE tbl_order o
    JOIN (
        SELECT
            oi.order_no,
            SUM(p.price * oi.order_item_quantity) AS total_price
        FROM tbl_orderitem oi
            JOIN tbl_product p ON oi.product_code = p.product_code
        WHERE oi.order_no = 8
        GROUP BY oi.order_no
    ) t ON o.order_no = t.order_no
SET o.order_totalprice = t.total_price
WHERE o.order_no = 9;


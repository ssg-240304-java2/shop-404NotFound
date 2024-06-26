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
INSERT INTO tbl_order (user_no, order_totalprice, created_at)
VALUES
    (1, 0, now()),
    (13, 0, now());

INSERT INTO tbl_orderitem (order_no, product_code, order_item_quantity)
VALUES
    (9, 3, 20),
    (9, 5, 11),
    (9, 12, 4),
    (10, 1, 6);

UPDATE
    tbl_order
SET
    order_totalprice = (SELECT
        SUM(p.price * oi.order_item_quantity) AS total_price
    FROM tbl_orderitem oi
        JOIN tbl_product p ON oi.product_code = p.product_code
    WHERE oi.order_no = 12
    GROUP BY oi.order_no)
where order_no = 12;

select * from tbl_order;





INSERT INTO tbl_order (user_no, order_totalprice, created_at)
VALUES
    (4, 0, now()),
    (9, 0, now()),
    (4, 0, now());

INSERT INTO tbl_orderitem (order_no, product_code, order_item_quantity)
VALUES
    (13, 9, 7),
    (14, 14, 3),
    (15, 12, 34),
    (13, 5, 2);

UPDATE
    tbl_order
SET
    order_totalprice = (SELECT
        SUM(p.price * oi.order_item_quantity) AS total_price
    FROM tbl_orderitem oi
        JOIN tbl_product p ON oi.product_code = p.product_code
    WHERE oi.order_no = 15
    GROUP BY oi.order_no)
where order_no = 15;

select * from tbl_order;





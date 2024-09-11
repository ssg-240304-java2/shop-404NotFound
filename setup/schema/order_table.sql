-- 1. 주문 테이블 삭제
DROP TABLE IF EXISTS tbl_order;
-- 2. 주문 상세 테이블 삭제
DROP TABLE IF EXISTS tbl_orderitem;

-- 주문 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_order (
    order_no INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '주문번호', -- 주문 번호
    user_no INT COMMENT '회원번호', -- 회원 번호
    order_totalprice INTEGER COMMENT '총금액', -- 총 주문 금액
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시', -- 생성 일시
    order_status VARCHAR(20) DEFAULT '주문접수' COMMENT '주문상태', -- 주문 상태 -> 기본 default값 주문접수
    FOREIGN KEY (user_no) REFERENCES tbl_user(user_no)
    );

-- 주문 상세 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_orderitem (
    order_item_no INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '주문항목번호', -- 주문 항목 번호
    order_no INTEGER COMMENT '주문번호', -- 주문 번호
    product_code INTEGER COMMENT '상품코드', -- 상품 코드
    order_item_quantity INTEGER COMMENT '주문상품개수', -- 주문 상품 개수
    FOREIGN KEY (order_no) REFERENCES tbl_order(order_no) ON DELETE CASCADE, -- tbl_order 테이블의 order_no를 참조
    FOREIGN KEY (product_code) REFERENCES tbl_product(product_code)
    );


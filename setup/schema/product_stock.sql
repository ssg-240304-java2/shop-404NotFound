use devshop;

# 초기화 쿼리
-- 6. 재고 테이블 삭제
DROP TABLE IF EXISTS `tbl_inventory_stock`;

-- 5. 입출고 이력 테이블 삭제
DROP TABLE IF EXISTS `tbl_inventory_transactions`;

-- 4. 상품정보 테이블 삭제
DROP TABLE IF EXISTS `tbl_product`;

-- 3. 상품 이미지 테이블 삭제
DROP TABLE IF EXISTS `tbl_product_image`;

-- 2. 카테고리 테이블 삭제
DROP TABLE IF EXISTS `tbl_category`;

-- 1. 입출고 작업 테이블 삭제
DROP TABLE IF EXISTS `tbl_inventory_tasks`;


# 생성 쿼리
# 1. 입출고 작업 테이블(작업 범례)
CREATE TABLE IF NOT EXISTS `tbl_inventory_tasks`
(
    `task_id`   INT         NOT NULL AUTO_INCREMENT COMMENT '작업코드',
    `task_name` VARCHAR(30) NOT NULL                COMMENT '작업명',
    PRIMARY KEY (`task_id`)
) COMMENT = '입출고 작업';

    # 데이터 유일성 보장(task_id)
    CREATE UNIQUE INDEX `tbl_inventory_tasks_PK` ON `tbl_inventory_tasks` (`task_id`);

# 2. 카테고리 테이블
CREATE TABLE IF NOT EXISTS `tbl_category`
(
    `category_code`     INT         NOT NULL AUTO_INCREMENT COMMENT '카테고리 코드',
    `category_name`     VARCHAR(30) NOT NULL                COMMENT '카테고리 명',
    `ref_category_code` INT                                 COMMENT '상위 카테고리 코드',
    PRIMARY KEY (`category_code`)
) COMMENT = '상품 카테고리';

ALTER TABLE tbl_category
ADD CONSTRAINT fk_ref_category_code
FOREIGN KEY (ref_category_code) REFERENCES tbl_category(category_code)
ON DELETE CASCADE ON UPDATE CASCADE;

    # 데이터 유일성 보장(category_code)
    CREATE UNIQUE INDEX `tbl_category_PK` ON `tbl_category` (`category_code`);

# 3. 상품 이미지 테이블
CREATE TABLE IF NOT EXISTS `tbl_product_image`
(
    `thumbnail_path`  INT         NOT NULL AUTO_INCREMENT   COMMENT '이미지 번호',
    `uuid_filename`   VARCHAR(50) NOT NULL                  COMMENT 'uuid 파일명',
    `origin_filename` VARCHAR(50) NOT NULL                  COMMENT '원본 파일명',
    PRIMARY KEY (`thumbnail_path`)
) COMMENT = '상품 이미지';

    # 데이터 유일성 보장(thumbnail_path)
    CREATE UNIQUE INDEX `tbl_product_image_PK` ON `tbl_product_image` (`thumbnail_path`);

# 4. 상품정보 테이블
CREATE TABLE IF NOT EXISTS `tbl_product`
(
    `product_code`      INT                                       NOT NULL AUTO_INCREMENT COMMENT '상품코드',
    `product_name`      VARCHAR(30)                               NOT NULL COMMENT '상품명',
    `price`             INT                                       NOT NULL COMMENT '판매가',
    `category_code`     INT                                       NOT NULL COMMENT '카테고리 코드',
    `is_displayed`      VARCHAR(1)    DEFAULT 'Y'                 NOT NULL COMMENT '노출 여부',
    `product_desc`      VARCHAR(1000) DEFAULT ''                  NOT NULL COMMENT '상품 설명',
    `thumbnail_path`    INT                                       NOT NULL COMMENT '이미지 번호',
    `registration_date` TIMESTAMP     DEFAULT current_timestamp() NOT NULL COMMENT '등록 일자',
    PRIMARY KEY (`product_code`),
    CONSTRAINT `fk_product_category` FOREIGN KEY (`category_code`)  REFERENCES `tbl_category` (`category_code`)         ON DELETE CASCADE,
    CONSTRAINT `fk_product_image`    FOREIGN KEY (`thumbnail_path`) REFERENCES `tbl_product_image` (`thumbnail_path`)   ON DELETE CASCADE
) COMMENT = '상품 정보 테이블';

    # 데이터 유일성 보장(product_code)
    CREATE UNIQUE INDEX `tbl_product_PK` ON `tbl_product` (`product_code`);

# 5. 입출고 이력 테이블
CREATE TABLE IF NOT EXISTS `tbl_inventory_transactions`
(
    `history_code`      INT                                   NOT NULL AUTO_INCREMENT   COMMENT '이력코드',
    `executed_at`       TIMESTAMP DEFAULT current_timestamp() NOT NULL                  COMMENT '수행일시',
    `operated_quantity` INT                                   NOT NULL                  COMMENT '처리수량',
    `task_id`           INT                                                             COMMENT '작업코드',
    `product_code`      INT                                                             COMMENT '상품코드',
    PRIMARY KEY (`history_code`),
    CONSTRAINT `fk_inventory_task`      FOREIGN KEY (`task_id`)         REFERENCES `tbl_inventory_tasks` (`task_id`)    ON DELETE CASCADE,
    CONSTRAINT `fk_inventory_product`   FOREIGN KEY (`product_code`)    REFERENCES `tbl_product` (`product_code`)       ON DELETE CASCADE
) COMMENT = '입출고 이력';

    # 데이터 유일성 보장(history_code)
    CREATE UNIQUE INDEX `tbl_inventory_transactions_PK` ON `tbl_inventory_transactions` (`history_code`);

# 6. 재고 테이블
CREATE TABLE IF NOT EXISTS `tbl_inventory_stock`
(
    `product_code`   INT           NOT NULL COMMENT '상품코드',
    `stock_quantity` INT DEFAULT 0 NOT NULL COMMENT '재고 수량',
    PRIMARY KEY (`product_code`),
    CONSTRAINT `fk_inventory_product_stock` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`) ON DELETE CASCADE
) COMMENT = '재고 테이블';

    # 데이터 유일성 보장(product_code)
    CREATE UNIQUE INDEX `tbl_inventory_stock_PK` ON `tbl_inventory_stock` (`product_code`);
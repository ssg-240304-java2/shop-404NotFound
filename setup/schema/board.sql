DROP TABLE IF EXISTS `tbl_board`;
CREATE TABLE `tbl_board` (
    `board_id` int NOT NULL AUTO_INCREMENT COMMENT '게시글번호',
    `title` varchar(100) NOT NULL COMMENT '제목',
    `content` varchar(3000) NOT NULL COMMENT '내용',
    `writer` varchar(20) NOT NULL COMMENT '작성자',
    `view_cnt` int(11) NOT NULL COMMENT '조회 수',
    `notice_yn` tinyint(1) NOT NULL COMMENT '공지글 여부',
    `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
    `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
    `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
    PRIMARY KEY (`board_id`)
) COMMENT '게시글';

DESC tbl_board; -- 테이블 구조 조회
SHOW TABLES; -- DB(스키마)에 포함된 모든 테이블 조회
SHOW FULL COLUMNS FROM tbl_board; -- 테이블에 포함된 모든 칼럼 조회 (코멘트 포함)

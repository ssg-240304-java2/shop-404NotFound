package com.nf404.devshop.board.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardResponse {

    private Long boardId;                  // 게시글번호
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시
}

package com.nf404.devshop.board.model.dto;

import lombok.*;

@Getter
@Setter
public class BoardRequest { // 게시글생성과 수정에 사용할 요청클래스
    private int boardId;        // 게시판번호
    private String title;       // 제목
    private String content;     // 내용
    private String writer;         // 작성자
    private Boolean noticeYn;   // 공지글 여부
}

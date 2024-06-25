package com.nf404.devshop.board.model.dao;

import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void saveBoard(BoardRequest params);

    /**
     * 게시글 상세정보 조회
     * @param boardId - 게시글 번호
     * @return 게시글 상세정보
     */
    BoardResponse findByBoardId(int boardId);

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void updateBoard(BoardRequest params);

    /**
     * 게시글 삭제
     * @param boardId - 게시글 번호
     */
    void deleteByBoardId(int boardId);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<BoardResponse> findAllBoard();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count();

}

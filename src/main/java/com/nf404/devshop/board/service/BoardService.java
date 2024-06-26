package com.nf404.devshop.board.service;

import com.nf404.devshop.board.mapper.BoardMapper;
import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 클래스 내 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 어노테이션
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 생성
     * @param params - 게시글 정보
     * @return 게시글 id
     */
    @Transactional
    public int saveBoard(BoardRequest params) {
        boardMapper.saveBoard(params);
        return params.getBoardId();
    }


    /**
     * 특정 게시글 상세정보 조회
     * @param boardId - 게시글 번호
     * @return 게시글 상세정보
     */
    public BoardResponse findByBoardId(int boardId) {
        return boardMapper.findByBoardId(boardId);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return 게시글 id
     */
    @Transactional
    public int updateBoard(BoardRequest params) {
        boardMapper.updateBoard(params);
        return params.getBoardId();
    }

    /**
     * 게시글 삭제
     * @param boardId - 게시글번호
     * @return 게시글 id
     */
    public int deleteByBoardId(int boardId) {
        boardMapper.deleteByBoardId(boardId);
        return boardId;
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<BoardResponse> findAllBoard() {
        return boardMapper.findAllBoard();
    }


}

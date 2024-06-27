package com.nf404.devshop.board.service;

import com.nf404.devshop.board.mapper.BoardMapper;
import com.nf404.devshop.board.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor // 클래스 내 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 어노테이션
@Slf4j
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 생성
     * @param params - 게시글 정보
     * @return 게시글 id
     */
    @Transactional
    public int saveBoard(BoardRequest params) {

        log.info("[BoardService] params >>>>>>>>>>>>>>>>>>>>>>> : {} ", params);
        boardMapper.saveBoard(params);
        return params.getBoardId();
    }

    /**
     * 게시글 리스트 조회
     * @return list
     */
    public List<BoardResponse> findAllBoard() {
        return boardMapper.findAllBoard();
    }


    /**
     * 게시글 선택 삭제
     * @param boardIds
     */
    @Transactional
    public void deleteByBoardIds(List<Integer> boardIds) {
        for (Integer boardId : boardIds) {
            boardMapper.deleteByBoardId(boardId);
        }
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

//    /**
//     * 게시글 리스트 조회
//     * @Param params - search conditions
//     * @return list & pagination information
//     */
//    public PagingResponse<BoardResponse> findAllBoard(SearchDTO params) {
//        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
//        int count = boardMapper.count(params);
//        if (count < 1 ) {
//            return new PagingResponse<>(Collections.emptyList(), null);
//        }
//
//        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDTO 타입의 객체인 params에 계산된 페이지 정보 저장
//        Pagination pagination = new Pagination(count, params);
//        params.setPagination(pagination);
//
//        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
//        List<BoardResponse> list = boardMapper.findAllBoard(params);
//        return new PagingResponse<>(list, pagination);
//    }


}

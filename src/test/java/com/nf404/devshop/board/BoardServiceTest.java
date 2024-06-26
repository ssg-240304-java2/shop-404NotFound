package com.nf404.devshop.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import com.nf404.devshop.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    @DisplayName("게시글 생성 테스트")
    void saveBoard() {
        BoardRequest params = new BoardRequest();
        params.setTitle("4번째 테스트 게시글");
        params.setContent("4번째 게시글 테스트 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        int boardId = boardService.saveBoard(params);
        System.out.println("생성된 게시글 id = " + boardId);
    }


/* 페이징 처리 전 게시판 리스트 조회 테스트*/
//    @Test // JUnit은 테스트 메소드들을 병렬로 실행하기 때문에 출력순서가 보장안됨
//    @DisplayName("게시판 리스트 조회 테스트")
//    void findAllBoard() throws JsonProcessingException {
//
//        List<BoardResponse> boardList = boardService.findAllBoard();
//        for (BoardResponse board : boardList) {
//            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
//            System.out.println("boardJson >>>>>>>>>>>>>>> = " + boardJson);
//        }
//
//    }

    // 페이징 처리 후 리스트 조회 테스트
    @Test
    @DisplayName("게시판 리스트 조회 테스트")
    void saveByForeach() {

        for (int i = 1; i <= 1000; i++) {
            BoardRequest params = new BoardRequest();
            params.setTitle(i + "번 게시글 제목");
            params.setContent(i + "번 게시글 내용");
            params.setWriter("테스터" + i);
            params.setNoticeYn(false);
            boardService.saveBoard(params);
        }
    }

    @Test
    @DisplayName("게시글번호로 게시글 조회")
    void findByBoardId() {
        BoardResponse board = boardService.findByBoardId(5);
        try {
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println("boardJson >>>>>>>>>>>>>>> = " + boardJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updateBoard() {
        // 1. 게시글 수정
        BoardRequest params = new BoardRequest();
        params.setBoardId(5);
        params.setTitle("5번 게시글로 제목 수정");
        params.setContent("5번 게시글로 내용 수정");
        params.setWriter("안준렬");
        params.setNoticeYn(false);
        boardService.updateBoard(params);

        // 2. 수정 후 조회
        BoardResponse board = boardService.findByBoardId(5);
        try {
            //조회한 5번 게시글의 응답객체를 JSON 문자열로 변환하여 출력
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println("수정 된 게시글 정보 >>>>>>>>>> : " + boardJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test // 삭제 테스트 데이터는 물리적으로 삭제되지 않고 삭제여부 상태 값을 0 -> 1로 변경
//    @DisplayName("삭제 테스트")
//    void delete() {
//        System.out.println("삭제 이전의 전체 게시글 개수 : " + boardService.findAllBoard().size());
//        boardService.deleteByBoardId(5);
//        System.out.println("삭제 이후의 전체 게시글 개수 : " + boardService.findAllBoard().size());
//
//    }

    @Test
    @DisplayName("테스트 데이터 삽입")
    void saveByBoardForeach() {
        for (int i = 1; i <= 200; i++) {
            BoardRequest params = new BoardRequest();
            params.setTitle(i + "번 게시글 제목");
            params.setContent(i + "번 게시글 내용");
            params.setWriter("테스터" + i);
            params.setNoticeYn(false);
            boardService.saveBoard(params);
        }
    }
}

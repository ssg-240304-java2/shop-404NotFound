package com.nf404.devshop.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nf404.devshop.board.mapper.BoardMapper;
import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    // 게시글 insert 테스트
    @Test
    @DisplayName("게시글 추가")
    void saveBoard() {
        BoardRequest params =  new BoardRequest();
        params.setTitle("첫번째 테스트 게시글");
        params.setContent("내용 들어가는지 테스트");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        boardMapper.saveBoard(params);

        List<BoardResponse> boards = boardMapper.findAllBoard();
        System.out.println("전체 게시글 개수는 : " + boards.size() + "개 입니다.");
    }


    @Test // 게시글 번호로 게시글 조회 테스트
    @DisplayName("게시글번호로 게시글 조회")
    void findBoardById() {
        BoardResponse board = boardMapper.findByBoardId(1);
        try{
            // Spring Boot에 내장되어있는 Jackson 라이브러리 이용해서
            // 조회한 1번 게시글의 응답객체를 JSON 문자열로 변환하여 출력
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(">>>>>>>>>>>>>>>> " + boardJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test // 게시글 수정 테스트
    @DisplayName("게시글 수정 테스트")
    void updateBoard() {
        // 1. 게시글 수정
        BoardRequest params = new BoardRequest();
        params.setBoardId(1);
        params.setTitle("1번 게시글 제목 수정");
        params.setContent("1번 게시글 내용 수정");
        params.setWriter("안준렬");
        params.setNoticeYn(true);
        boardMapper.updateBoard(params);
        
        // 2. 게시글 상세정보 조회
        BoardResponse board = boardMapper.findByBoardId(3);
        try{
            // Spring Boot에 내장되어있는 Jackson 라이브러리 이용해서
            // 조회한 1번 게시글의 응답객체를 JSON 문자열로 변환하여 출력
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
            System.out.println(">>>>>>>>>>>>>>>> "  + boardJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test // 삭제 테스트 데이터는 물리적으로 삭제되지 않고 삭제여부 상태 값을 0 -> 1로 변경
    @DisplayName("삭제 테스트")
    void delete() {
        System.out.println("삭제 이전의 전체 게시글 개수 : " + boardMapper.findAllBoard().size());
        boardMapper.deleteByBoardId(2);
        System.out.println("삭제 이후의 전체 게시글 개수 : " + boardMapper.findAllBoard().size());

    }



}

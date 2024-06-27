package com.nf404.devshop.board.controller;

import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import com.nf404.devshop.board.model.dto.PagingResponse;
import com.nf404.devshop.board.model.dto.SearchDTO;
import com.nf404.devshop.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성 페이지
    @GetMapping("/board/write")
    public String openBoardWrite(@RequestParam(value = "boardId", required = false) Integer boardId, Model model) {
        // boardId가 null 이면 신규등록, != null 일시 수정
        if (boardId != null) {
            BoardResponse board =  boardService.findByBoardId(boardId);
            log.info("[BoardController] board >>>>>>>>>>>>>>>>>>>>>>> : {} ", board);

            model.addAttribute("board", board);

        }
        return "board/write";
    }


    // 게시글 리스트 페이지
    @GetMapping("/board/list")
    public String openBoardList(Model model) {
        List<BoardResponse> allBoard = boardService.findAllBoard();

        model.addAttribute("boards", allBoard);
        return "board/list";
    }

    // 기존 게시글 수정
    @PostMapping("/board/update")
    public String updateBoard(BoardRequest params) {
        boardService.updateBoard(params);
        return "redirect:/board/list";
    }


    // 게시물 삭제
    @PostMapping("/board/list/delete")
    public String deleteBoard(@RequestBody List<Integer> boardIds) {
        System.out.println("boardIds = " + boardIds);
        boardService.deleteByBoardIds(boardIds);

        return "redirect:/board/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/board/view/{boardId}")
    public String openBoardView(@PathVariable Integer boardId, Model model) {
        BoardResponse board = boardService.findByBoardId(boardId);
        log.info("[BoardController] board >>>>>>>>>>>>>>>> : {} ", board);
        model.addAttribute("board", board);
        System.out.println("TESTEST");
        return "board/view";
    }

    // 신규 게시글 생성
    @PostMapping("/board/save")
    public String saveBoard(BoardRequest params) {
        boardService.saveBoard(params);
        return "redirect:/board/list";
    }

// 게시글 상세보기
//    @GetMapping("/board/detail/{boardId}")
//    public String openBoardDetail(@PathVariable Integer boardId, Model model) {
//        BoardResponse board = boardService.findByBoardId(boardId);
//        String title = board.getTitle();
//        String content = board.getContent();
//
//        model.addAttribute("title", title);
//        model.addAttribute("content", content);
//
//        return "board/detail";
//    }

    // 게시글 리스트 페이지 페이징 뺀부분
//    @GetMapping("/board/boardlist")
//    public String openBoardList(Model model) {
//        List<BoardResponse> boards = boardService.findAllBoards();
//        model.addAttribute("boards", boards);
//        return "board/boardlist";
//    }

//    // 게시글 삭제
//    @PostMapping("/board/delete")
//    public String deleteBoard(@RequestParam Integer boardId, SearchDTO queryParams, Model model) {
//        boardService.deleteByBoardId(boardId);
//        return "redirect:/board/list";
//    }

}
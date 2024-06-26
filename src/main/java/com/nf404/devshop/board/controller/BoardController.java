package com.nf404.devshop.board.controller;

import com.nf404.devshop.board.model.dto.BoardRequest;
import com.nf404.devshop.board.model.dto.BoardResponse;
import com.nf404.devshop.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // 신규 게시글 생성
    @PostMapping("/board/save")
    public String saveBoard(BoardRequest params) {
        boardService.saveBoard(params);
        return "redirect:/board/list";
    }

    // 게시글 리스트 페이지
    @GetMapping("/board/list")
    public String openBoardList(Model model) {
        List<BoardResponse> boards = boardService.findAllBoard();
        log.info("[BoardController] boards >>>>>>>>>>>>>>>> : {} ", boards);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/board/view")
    public String openBoardView(@RequestParam Integer boardId, Model model) {
        BoardResponse board = boardService.findByBoardId(boardId);
        log.info("[BoardController] board >>>>>>>>>>>>>>>> : {} ", board);
        model.addAttribute("board", board);
        return "board/view";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update")
    public String updateBoard(BoardRequest params) {
        boardService.updateBoard(params);
        return "redirect:/board/list";
    }


}
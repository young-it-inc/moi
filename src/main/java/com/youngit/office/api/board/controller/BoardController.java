package com.youngit.office.api.board.controller;

import com.youngit.office.api.board.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Tag(name = "게시판 관리")
@RestController
public class BoardController {

    private final BoardService boardService;

    private static final Logger logger = Logger.getLogger(BoardController.class.getName());

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
}

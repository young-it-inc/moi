package com.youngit.office.api.board.service;

import com.youngit.office.api.board.mapper.BoardMapper;
import com.youngit.office.api.board.mapstruct.BoardMapstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardMapstruct boardMapstruct;
    @Autowired
    public BoardService(BoardMapper boardMapper, BoardMapstruct boardMapstruct) {
        this.boardMapper = boardMapper;
        this.boardMapstruct = boardMapstruct;
    }

}

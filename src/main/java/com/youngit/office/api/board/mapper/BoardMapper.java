package com.youngit.office.api.board.mapper;

import com.youngit.office.api.board.model.BoardModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardModel> getOrSearchListBoard();
    int countGetOrSearchListBoard();
    BoardModel getOneBoard(int boardId);

}

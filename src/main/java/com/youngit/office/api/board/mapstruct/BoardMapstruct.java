package com.youngit.office.api.board.mapstruct;

import com.youngit.office.api.board.dto.BoardDto;
import com.youngit.office.api.board.model.BoardModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapstruct {
    BoardModel toModel(BoardDto boardDto);
    BoardDto toDto(BoardModel boardModel);
}

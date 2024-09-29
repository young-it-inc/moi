package com.youngit.office.api.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "")
public class BoardSearchDto {
    String searchKey;
    String searchValue;
}

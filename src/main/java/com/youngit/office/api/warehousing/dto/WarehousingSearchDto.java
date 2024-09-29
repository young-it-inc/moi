package com.youngit.office.api.warehousing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description ="")
public class WarehousingSearchDto {
    String searchKey;
    String searchValue;
}

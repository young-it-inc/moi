package com.youngit.office.api.code.mapstruct;

import com.youngit.office.api.code.dto.AutotextDto;
import com.youngit.office.api.code.dto.CommonCodeDto;
import com.youngit.office.api.code.model.AutotextModel;
import com.youngit.office.api.code.model.CommonCodeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeMapstruct {

    AutotextModel toModel(AutotextDto autotextDto);
    AutotextDto toDto(AutotextModel autotextModel);
    CommonCodeModel toModel(CommonCodeDto commonCodeDto);
    CommonCodeDto toDto(CommonCodeModel commonCodeModel);


}

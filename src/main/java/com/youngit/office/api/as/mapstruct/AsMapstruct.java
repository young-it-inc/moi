package com.youngit.office.api.as.mapstruct;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.model.AsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AsMapstruct {
    AsDto toDto(AsModel asModel);
    AsModel toModel(AsDto asDto);

}

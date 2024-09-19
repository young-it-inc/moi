package com.youngit.office.api.office.mapstruct;

import com.youngit.office.api.office.dto.OfficeDto;
import com.youngit.office.api.office.model.OfficeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface OfficeMapstruct {
    OfficeModel toModel(OfficeDto officeDto);
    OfficeDto toDto(OfficeModel officeModel);

}

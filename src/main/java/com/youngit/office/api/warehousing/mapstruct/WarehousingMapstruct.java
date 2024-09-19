package com.youngit.office.api.warehousing.mapstruct;

import com.youngit.office.api.warehousing.dto.WarehousingDto;
import com.youngit.office.api.warehousing.model.WarehousingModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehousingMapstruct {

    WarehousingDto toDto(WarehousingModel warehousingModel);
    WarehousingModel toModel(WarehousingDto warehousingDto);
}

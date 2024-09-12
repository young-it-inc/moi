package com.youngit.office.api.warehousing.mapper;

import com.youngit.office.api.warehousing.dto.WarehousingDto;
import com.youngit.office.api.warehousing.model.WarehousingModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehousingMapstructMapper {

    WarehousingDto toDto(WarehousingModel warehousingModel);
    WarehousingModel toModel(WarehousingDto warehousingDto);
}

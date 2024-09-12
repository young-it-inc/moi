package com.youngit.office.api.as.mapper;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.model.AsModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AsMapstructMapper {

    AsDto toDto(AsModel asModel);
    AsModel toModel(AsDto asDto);
    List<AsDto> toDtoList(List<AsModel> asModelList);
    List<AsModel> toModelList(List<AsDto> asDtoList);


}

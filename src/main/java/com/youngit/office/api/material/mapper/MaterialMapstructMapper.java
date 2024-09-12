package com.youngit.office.api.material.mapper;

import com.youngit.office.api.material.dto.MaterialDto;
import com.youngit.office.api.material.model.MaterialModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapstructMapper {

    MaterialDto toDto(MaterialModel materialModel);
    MaterialModel toModel(MaterialDto materialDto);
    List<MaterialDto> toDtoList(List<MaterialModel> materialModelList);
    List<MaterialModel> toModelList(List<MaterialDto> materialDtoList);

}

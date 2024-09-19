package com.youngit.office.api.material.mapstruct;

import com.youngit.office.api.material.dto.MaterialDto;
import com.youngit.office.api.material.model.MaterialModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapstruct {
    MaterialDto toDto(MaterialModel materialModel);
    MaterialModel toModel(MaterialDto materialDto);
}

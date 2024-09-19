package com.youngit.office.api.install.mapstruct;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.model.InstallModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstallMapstruct {
    InstallModel toModel(InstallDto installDto);
    InstallDto toDto(InstallModel installModel);

}

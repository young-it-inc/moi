package com.youngit.office.api.installer.mapstruct;

import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.model.InstallerModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstallerMapstruct {
    InstallerDto toDto(InstallerModel installerModel);
    InstallerModel toModel(InstallerDto installerDto);
}

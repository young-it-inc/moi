package com.youngit.office.api.install.mapper;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.model.InstallModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstallMapstructMapper {

    InstallModel toModel(InstallDto installDto);
    InstallDto toDto(InstallModel installModel);

    List<InstallModel> toModelList(List<InstallDto> installDtoList);
    List<InstallDto> toDtoList(List<InstallModel> installModelList);


}

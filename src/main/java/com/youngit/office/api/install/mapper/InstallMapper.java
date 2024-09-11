package com.youngit.office.api.install.mapper;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.model.InstallModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallMapper {

    InstallModel toModel(InstallDto installDto);
    InstallDto toDto(InstallModel installModel);

    List<InstallModel> toModelList(List<InstallDto> installDtoList);
    List<InstallDto> toDtoList(List<InstallModel> installModelList);


    List<InstallModel> getListInstall(String installStateCode);
    int getCountListInstall(String installStateCode);
    InstallModel getOneInstall(String installUniqId);


    int registerInstall(InstallModel installModel);

    int updateInstall(InstallModel installModel);

    int deleteInstall(String installUniqId);


    String getLastInstallUniqId();
}

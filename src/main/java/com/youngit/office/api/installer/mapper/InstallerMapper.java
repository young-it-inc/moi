package com.youngit.office.api.installer.mapper;

import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.model.InstallerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallerMapper {

    InstallerDto toDto(InstallerModel installerModel);
    InstallerModel toModel(InstallerDto installerDto);
    List<InstallerDto> toDtoList(List<InstallerModel> installerModelList);
    List<InstallerModel> toModelList(List<InstallerDto> installerDtoList);


    //설치팀계약 리스트 조회
    List<InstallerModel> getListInstaller();
    int getCountListInstaller();
    InstallerModel getOneInstaller(String installerUniqNo);

    int registerInstaller(InstallerModel installerModel);

    int updateInstaller(InstallerModel installerModel);

    int deleteInstaller(String installerUniqNo);




}

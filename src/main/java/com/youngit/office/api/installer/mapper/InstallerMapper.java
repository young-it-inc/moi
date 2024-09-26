package com.youngit.office.api.installer.mapper;

import com.youngit.office.api.installer.model.InstallerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallerMapper {


    //설치팀계약 리스트 조회
    List<InstallerModel> getOrSearchListInstaller();
    int countGetOrSearchListInstaller();
    InstallerModel getOneInstaller(String installerUniqNo);

    int registerInstaller(InstallerModel installerModel);

    int updateInstaller(InstallerModel installerModel);

    int deleteInstaller(String installerUniqNo);




}

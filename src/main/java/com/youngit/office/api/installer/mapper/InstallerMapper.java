package com.youngit.office.api.installer.mapper;

import com.youngit.office.api.installer.model.InstallerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallerMapper {

    List<InstallerModel> getListInstaller();

    int registerInstaller(InstallerModel installerModel);

    int updateInstaller(InstallerModel installerModel);

    int deleteInstaller(String installerUniqNo);




}

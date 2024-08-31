package com.youngit.office.api.install.mapper;

import com.youngit.office.api.install.model.InstallModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallMapper {

    List<InstallModel> getListInstall(String installStateCode);
    int getCountListInstall(String installStateCode);
    InstallModel getOneInstall(String installUniqId);


    int registerInstall(InstallModel installModel);

    int updateInstall(InstallModel installModel);

    int deleteInstall(InstallModel installModel);


    String getLastInstallUniqId();
}

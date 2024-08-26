package com.youngit.office.api.install.mapper;

import com.youngit.office.api.install.model.InstallModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallMapper {

    int registerInstall(InstallModel installModel);

    int updateInstall(InstallModel installModel);

    int deleteInstall(InstallModel installModel);

    List<InstallModel> getListInstall();

    String getLastInstallUniqId();
}

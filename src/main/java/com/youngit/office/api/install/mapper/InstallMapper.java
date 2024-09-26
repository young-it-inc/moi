package com.youngit.office.api.install.mapper;

import com.youngit.office.api.install.dto.InstallSearchDto;
import com.youngit.office.api.install.model.InstallModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstallMapper {

    List<InstallModel> getOrSearchListInstall(InstallSearchDto installSearchDto);
    int countGetOrSearchListInstall(InstallSearchDto installSearchDto);
    InstallModel getOneInstall(String installUniqId);

    int registerInstall(InstallModel installModel);

    int updateInstall(InstallModel installModel);

    int deleteInstall(String installUniqId);

    String getLastInstallUniqId();
}

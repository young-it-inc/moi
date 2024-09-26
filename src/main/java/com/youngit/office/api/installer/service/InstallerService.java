package com.youngit.office.api.installer.service;

import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.mapper.InstallerMapper;
import com.youngit.office.api.installer.mapstruct.InstallerMapstruct;
import com.youngit.office.api.installer.model.InstallerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallerService {

    private final InstallerMapper installerMapper;

    private final InstallerMapstruct installerMapstruct;

    @Autowired
    public InstallerService(InstallerMapper installerMapper, InstallerMapstruct installerMapstruct) {
        this.installerMapper = installerMapper;
        this.installerMapstruct = installerMapstruct;
    }


    public List<InstallerDto> getListInstaller() {
        List<InstallerModel> resultModelList = installerMapper.getOrSearchListInstaller();
        return resultModelList.stream().map(installerMapstruct::toDto).toList();
    }

    public int countGetOrSearchListInstaller() {
        return installerMapper.countGetOrSearchListInstaller();
    }
    public int registerInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = installerMapstruct.toModel(installerDto);
        return installerMapper.registerInstaller(installerModel);
    }

    public int updateInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = installerMapstruct.toModel(installerDto);
        return installerMapper.updateInstaller(installerModel);
    }

    public int deleteInstaller(String installerUniqNo) {
        return installerMapper.deleteInstaller(installerUniqNo);
    }

}

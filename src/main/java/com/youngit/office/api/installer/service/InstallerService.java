package com.youngit.office.api.installer.service;

import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.mapper.InstallerMapper;
import com.youngit.office.api.installer.mapper.InstallerMapstructMapper;
import com.youngit.office.api.installer.model.InstallerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallerService {

    private final InstallerMapper installerMapper;
    private final InstallerMapstructMapper installerMapstructMapper;

    @Autowired
    public InstallerService(InstallerMapper installerMapper, InstallerMapstructMapper installerMapstructMapper) {
        this.installerMapper = installerMapper;
        this.installerMapstructMapper = installerMapstructMapper;
    }

    public List<InstallerDto> getListInstaller() {
        List<InstallerModel> resultModelList = installerMapper.getListInstaller();
        return resultModelList.stream().map(installerMapstructMapper::toDto).toList();
    }

    public int getCountListInstaller() {
        return installerMapper.getCountListInstaller();
    }
    public int registerInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = installerMapstructMapper.toModel(installerDto);
        return installerMapper.registerInstaller(installerModel);
    }

    public int updateInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = installerMapstructMapper.toModel(installerDto);
        return installerMapper.updateInstaller(installerModel);
    }

    public int deleteInstaller(String installerUniqNo) {
        return installerMapper.deleteInstaller(installerUniqNo);
    }

}

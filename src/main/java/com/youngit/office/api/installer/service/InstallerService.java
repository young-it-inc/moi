package com.youngit.office.api.installer.service;

import com.youngit.office.api.installer.mapper.InstallerMapper;
import com.youngit.office.api.installer.model.InstallerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallerService {

    @Autowired
    InstallerMapper installerMapper;

    public List<InstallerModel> getListInstaller() {
        return installerMapper.getListInstaller();
    }

    public int registerInstaller(InstallerModel installerModel) {
        return installerMapper.registerInstaller(installerModel);
    }

    public int updateInstaller(InstallerModel installerModel) {
        return installerMapper.updateInstaller(installerModel);
    }

    public int deleteInstaller(String installerUniqNo) {
        return installerMapper.deleteInstaller(installerUniqNo);
    }

}

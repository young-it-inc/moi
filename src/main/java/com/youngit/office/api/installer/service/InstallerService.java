package com.youngit.office.api.installer.service;

import com.youngit.office.api.installer.dto.InstallerDto;
import com.youngit.office.api.installer.mapper.InstallerMapper;
import com.youngit.office.api.installer.model.InstallerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallerService {

    private final InstallerMapper installerMapper;

    @Autowired
    public InstallerService(InstallerMapper installerMapper) {
        this.installerMapper = installerMapper;
    }

    public InstallerDto convertToDto(InstallerModel installerModel) {
        return installerMapper.toDto(installerModel);
    }

    public List<InstallerDto> convertToDtoList(List<InstallerModel> installerModelList) {
        return installerMapper.toDtoList(installerModelList);
    }

    public InstallerModel convertToModel(InstallerDto installerDto) {
        return installerMapper.toModel(installerDto);
    }

    public List<InstallerModel> convertToModelList(List<InstallerDto> installerDtoList) {
        return installerMapper.toModelList(installerDtoList);
    }

    public List<InstallerDto> getListInstaller() {
        List<InstallerModel> resultModelList = installerMapper.getListInstaller();
        return convertToDtoList(resultModelList);
    }

    public int getCountListInstaller() {
        return installerMapper.getCountListInstaller();
    }
    public int registerInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = convertToModel(installerDto);
        return installerMapper.registerInstaller(installerModel);
    }

    public int updateInstaller(InstallerDto installerDto) {
        InstallerModel installerModel = convertToModel(installerDto);
        return installerMapper.updateInstaller(installerModel);
    }

    public int deleteInstaller(String installerUniqNo) {
        return installerMapper.deleteInstaller(installerUniqNo);
    }

}

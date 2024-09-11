package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.model.InstallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallService {

    @Autowired
    InstallMapper installMapper;

    public InstallDto convertToDto(InstallModel installModel) {
        return installMapper.toDto(installModel);
    }
    public InstallModel convertToModel(InstallDto installDto) {
        return installMapper.toModel(installDto);
    }
    public List<InstallDto> convertToDtoList(List<InstallModel> installModelList) {
        return installMapper.toDtoList(installModelList);
    }
    public List<InstallModel> convertToModelList(List<InstallDto> installDtoList) {
        return installMapper.toModelList(installDtoList);
    }

    public List<InstallDto> getListInstall(String installStateCode) {
        List<InstallModel> resultModelList = installMapper.getListInstall(installStateCode);
        return convertToDtoList(resultModelList);
    }
    public int getCountListInstall(String installStateCode) {
        return installMapper.getCountListInstall(installStateCode);
    }
    public InstallDto getOneInstall(String installUniqId) {
        InstallModel resultModel = installMapper.getOneInstall(installUniqId);
        return convertToDto(resultModel);
    }

    public int registerInstall(InstallDto installDto) {
        //installUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastInstallUniqId = installMapper.getLastInstallUniqId();
        String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
        installDto.setInstallUniqId(newInstallUniqId);
        InstallModel installModel = convertToModel(installDto);
        return installMapper.registerInstall(installModel);
    }
    private String generateNewInstallUniqId(String lastInstallUniqId) {
        if(lastInstallUniqId == null)
            return "INSTL_00000000000001";

        String prefix = "INSTL_";
        String numberPart = lastInstallUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNumberStr = String.format("%014d", newNumber);
        return prefix + newNumberStr;
    }


    public int updateInstall(InstallDto installDto) {
        InstallModel installModel = installMapper.toModel(installDto);
        return installMapper.updateInstall(installModel);
    }

    public int deleteInstall(String installUniqId) {
        return installMapper.deleteInstall(installUniqId);
    }


}

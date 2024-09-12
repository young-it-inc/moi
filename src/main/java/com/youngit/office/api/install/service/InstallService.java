package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.mapper.InstallMapstructMapper;
import com.youngit.office.api.install.model.InstallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallService {


    private final InstallMapper installMapper;
    private final InstallMapstructMapper installMapstructMapper;

    @Autowired
    public InstallService(InstallMapper installMapper, InstallMapstructMapper installMapstructMapper) {
        this.installMapper = installMapper;
        this.installMapstructMapper = installMapstructMapper;
    }


    public List<InstallDto> getListInstall(String installStateCode) {
        List<InstallModel> resultModelList = installMapper.getListInstall(installStateCode);
        return resultModelList.stream().map(installMapstructMapper::toDto).toList();
    }
    public int getCountListInstall(String installStateCode) {
        return installMapper.getCountListInstall(installStateCode);
    }
    public InstallDto getOneInstall(String installUniqId) {
        InstallModel resultModel = installMapper.getOneInstall(installUniqId);
        return installMapstructMapper.toDto(resultModel);
    }

    public int registerInstall(InstallDto installDto) {
        //installUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastInstallUniqId = installMapper.getLastInstallUniqId();
        String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
        installDto.setInstallUniqId(newInstallUniqId);
        InstallModel installModel = installMapstructMapper.toModel(installDto);
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
        InstallModel installModel = installMapstructMapper.toModel(installDto);
        return installMapper.updateInstall(installModel);
    }

    public int deleteInstall(String installUniqId) {
        return installMapper.deleteInstall(installUniqId);
    }


}

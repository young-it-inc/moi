package com.youngit.office.api.install.service;

import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.model.InstallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallService {

    @Autowired
    InstallMapper installMapper;

    public List<InstallModel> getListInstall() {

        return installMapper.getListInstall();
    }

    public int registerInstall(InstallModel installModel) {
        //installUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastInstallUniqId = installMapper.getLastInstallUniqId();
        String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
        installModel.setInstallUniqId(newInstallUniqId);
        return installMapper.registerInstall(installModel);
    }
    private String generateNewInstallUniqId(String lastInstallUniqId) {
        if(lastInstallUniqId == null) {
            return "INSTL_00000000000001";
        }
        String prefix = "INSTL_";
        String numberPart = lastInstallUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNumberStr = String.format("%014d", newNumber);
        return prefix + newNumberStr;
    }


    public int updateInstall(InstallModel installModel) {
        return installMapper.updateInstall(installModel);
    }

    public int deleteInstall(InstallModel installModel) {
        return installMapper.deleteInstall(installModel);
    }


}

package com.youngit.office.api.install.service;

import com.youngit.office.api.install.dto.InstallDto;
import com.youngit.office.api.install.mapper.InstallMapper;
import com.youngit.office.api.install.mapstruct.InstallMapstruct;
import com.youngit.office.api.install.model.InstallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstallService {

    private final InstallMapper installMapper;
    private final InstallMapstruct installMapstruct;
    @Autowired
    public InstallService(InstallMapper installMapper, InstallMapstruct installMapstruct) {
        this.installMapper = installMapper;
        this.installMapstruct = installMapstruct;
    }

    /**
     * 설치 조회
     * @param installStateCode
     * @return
     */
    public List<InstallDto> getListInstall(String installStateCode) {
        List<InstallModel> resultModelList = installMapper.getListInstall(installStateCode);
        return resultModelList.stream().map(installMapstruct::toDto).toList();
    }
    public int getCountListInstall(String installStateCode) {
        return installMapper.getCountListInstall(installStateCode);
    }
    public InstallDto getOneInstall(String installUniqId) {
        InstallModel resultModel = installMapper.getOneInstall(installUniqId);
        return installMapstruct.toDto(resultModel);
    }

    /**
     * 설치 등록
     * @param installDto
     * @return
     */
    public int registerInstall(InstallDto installDto) {
        //installUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastInstallUniqId = installMapper.getLastInstallUniqId();
        String newInstallUniqId = generateNewInstallUniqId(lastInstallUniqId);
        installDto.setInstallUniqId(newInstallUniqId);
        InstallModel installModel = installMapstruct.toModel(installDto);
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

    /**
     * 설치 일괄 엑셀 등록
     * @param installDtoList
     * @return
     */
    public int registerInstallList(List<InstallDto> installDtoList) {
        for(InstallDto installDto : installDtoList) {
            registerInstall(installDto);
        }
        return 1;
    }
    /**
     * 설치 수정
     */
    public int updateInstall(InstallDto installDto) {
        InstallModel installModel = installMapstruct.toModel(installDto);
        return installMapper.updateInstall(installModel);
    }

    /**
     * 설치 삭제
     */
    public int deleteInstall(String installUniqId) {
        return installMapper.deleteInstall(installUniqId);
    }


}

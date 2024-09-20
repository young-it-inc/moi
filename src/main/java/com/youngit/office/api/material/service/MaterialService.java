package com.youngit.office.api.material.service;

import com.youngit.office.api.material.dto.MaterialDto;
import com.youngit.office.api.material.dto.MaterialSearchDto;
import com.youngit.office.api.material.mapper.MaterialMapper;
import com.youngit.office.api.material.mapstruct.MaterialMapstruct;
import com.youngit.office.api.material.model.MaterialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialService {

    private final MaterialMapper materialMapper;
    private final MaterialMapstruct materialMapstruct;


    @Autowired
    public MaterialService(MaterialMapper materialMapper, MaterialMapstruct materialMapstruct) {
        this.materialMapper = materialMapper;
        this.materialMapstruct = materialMapstruct;
    }

    /**
     * 자재 조회
     * @return
     */
    public List<MaterialDto> getOrSearchListMaterial(MaterialSearchDto materialSearchDto) {
        List<MaterialModel> result = materialMapper.getOrSearchListMaterial(materialSearchDto);
        return result.stream().map(materialMapstruct::toDto).toList();
    }

    public int countGetOrSearchListMaterial(MaterialSearchDto materialSearchDto) {
        return materialMapper.countGetOrSearchListMaterial(materialSearchDto);
    }

    public MaterialDto getOneMaterial(String materialUniqId)
    {
        MaterialModel materialModel = materialMapper.getOneMaterial(materialUniqId);
        return materialMapstruct.toDto(materialModel);
    }

    /**
     * 자재 등록
     * @param materialDto
     * @return
     */
    public int registerMaterial(MaterialDto materialDto) {
        String lastMaterialUniqId = materialMapper.getLastMaterialUniqId();
        String newMaterialUniqId = generateNewMaterialUniqId(lastMaterialUniqId);
        materialDto.setMaterialUniqId(newMaterialUniqId);
        MaterialModel materialModel = materialMapstruct.toModel(materialDto);
        int result = materialMapper.registerMaterial(materialModel);
        return result;
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
    private String generateNewMaterialUniqId(String lastMaterialUniqId) {
        String newMaterialUniqId = "";
        if (lastMaterialUniqId == null) {
            newMaterialUniqId = "MTRIL_00000000000001";
        } else {
            String lastMaterialUniqIdNumber = lastMaterialUniqId.substring(8);
            long newMaterialUniqIdNumber = Long.parseLong(lastMaterialUniqIdNumber) + 1;
            newMaterialUniqId = "MTRIL_" + String.format("%014d", newMaterialUniqIdNumber);
        }
        return newMaterialUniqId;
    }

    /**
     * 자재 수정
     * @param materialDto
     * @return
     */
    public int updateMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapstruct.toModel(materialDto);
        int result = materialMapper.updateMaterial(materialModel);
        return result;
    }

    /**
     * 자재 삭제
     * @param materialUniqId
     * @return
     */
    public int deleteMaterial(String materialUniqId) {
        int result = materialMapper.deleteMaterial(materialUniqId);
        return result;
    }
}

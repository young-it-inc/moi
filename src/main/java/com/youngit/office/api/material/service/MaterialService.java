package com.youngit.office.api.material.service;

import com.youngit.office.api.material.dto.MaterialDto;
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

    public List<MaterialDto> getListMaterial() {
        List<MaterialModel> result = materialMapper.getListMaterial();
        return result.stream().map(materialMapstruct::toDto).toList();
    }

    public MaterialDto getOneMaterial(String materialUniqId)
    {
        MaterialModel materialModel = materialMapper.getOneMaterial(materialUniqId);
        return materialMapstruct.toDto(materialModel);
    }

    public int registerMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapstruct.toModel(materialDto);
        int result = materialMapper.registerMaterial(materialModel);
        return result;
    }

    public int updateMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapstruct.toModel(materialDto);
        int result = materialMapper.updateMaterial(materialModel);
        return result;
    }

    public int deleteMaterial(String materialUniqId) {
        int result = materialMapper.deleteMaterial(materialUniqId);
        return result;
    }
}

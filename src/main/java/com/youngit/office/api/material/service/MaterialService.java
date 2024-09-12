package com.youngit.office.api.material.service;

import com.youngit.office.api.material.dto.MaterialDto;
import com.youngit.office.api.material.mapper.MaterialMapper;
import com.youngit.office.api.material.mapper.MaterialMapstructMapper;
import com.youngit.office.api.material.model.MaterialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialService {

    private final MaterialMapper materialMapper;
    private final MaterialMapstructMapper materialMapstructMapper;

    @Autowired
    public MaterialService(MaterialMapper materialMapper, MaterialMapstructMapper materialMapstructMapper) {
        this.materialMapper = materialMapper;
        this.materialMapstructMapper = materialMapstructMapper;
    }

    public List<MaterialDto> getListMaterial() {
        List<MaterialModel> result = materialMapper.getListMaterial();
        return result.stream().map(materialMapstructMapper::toDto).toList();
    }
    public int getCountListMaterial() {
        int result = materialMapper.getCountListMaterial();
        return result;
    }

    public MaterialDto getOneMaterial(String materialUniqId)
    {
        MaterialModel materialModel = materialMapper.getOneMaterial(materialUniqId);
        return materialMapstructMapper.toDto(materialModel);
    }

    public int registerMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapstructMapper.toModel(materialDto);
        int result = materialMapper.registerMaterial(materialModel);
        return result;
    }

    public int updateMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapstructMapper.toModel(materialDto);
        int result = materialMapper.updateMaterial(materialModel);
        return result;
    }

    public int deleteMaterial(String materialUniqId) {
        int result = materialMapper.deleteMaterial(materialUniqId);
        return result;
    }

}

package com.youngit.office.api.material.service;

import com.youngit.office.api.material.dto.MaterialDto;
import com.youngit.office.api.material.mapper.MaterialMapper;
import com.youngit.office.api.material.model.MaterialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialMapper materialMapper;

    @Autowired
    public MaterialService(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    public MaterialDto convertToDto(MaterialModel materialModel) {
        return materialMapper.toDto(materialModel);
    }
    public List<MaterialDto> convertToDtoList(List<MaterialModel> materialModelList) {
        return materialMapper.toDtoList(materialModelList);
    }
    public MaterialModel convertToModel(MaterialDto materialDto) {
        return materialMapper.toModel(materialDto);
    }
    public List<MaterialModel> convertToModelList(List<MaterialDto> materialDtoList) {
        return materialMapper.toModelList(materialDtoList);
    }

    public List<MaterialDto> getListMaterial() {
        List<MaterialModel> result = materialMapper.getListMaterial();
        return convertToDtoList(result);
    }
    public int getCountListMaterial() {
        int result = materialMapper.getCountListMaterial();
        return result;
    }

    public MaterialDto getOneMaterial(String materialUniqId)
    {
        MaterialModel materialModel = materialMapper.getOneMaterial(materialUniqId);
        return convertToDto(materialModel);
    }

    public int registerMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapper.toModel(materialDto);
        int result = materialMapper.registerMaterial(materialModel);
        return result;
    }

    public int updateMaterial(MaterialDto materialDto) {
        MaterialModel materialModel = materialMapper.toModel(materialDto);
        int result = materialMapper.updateMaterial(materialModel);
        return result;
    }

    public int deleteMaterial(String materialUniqId) {
        int result = materialMapper.deleteMaterial(materialUniqId);
        return result;
    }

}

package com.youngit.office.api.material.service;

import com.youngit.office.api.material.mapper.MaterialMapper;
import com.youngit.office.api.material.model.MaterialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    public List<MaterialModel> getListMaterial() {
        List<MaterialModel> result = materialMapper.getListMaterial();
        return result;
    }

    public int registerMaterial(MaterialModel materialModel)   {
        return materialMapper.registerMaterial(materialModel);
    }

    public int updateMaterial(MaterialModel materialModel) {
        return materialMapper.updateMaterial(materialModel);
    }

    public int deleteMaterial(String materialId) {
        return materialMapper.deleteMaterial(materialId);
    }

}

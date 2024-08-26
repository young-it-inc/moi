package com.youngit.office.api.material.mapper;

import com.youngit.office.api.material.model.MaterialModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {

    List<MaterialModel> getListMaterial();

    int registerMaterial(MaterialModel materialModel);

    int updateMaterial(MaterialModel materialModel);

    int deleteMaterial(String materialId);

}



package com.youngit.office.api.material.mapper;

import com.youngit.office.api.material.model.MaterialModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {

    //자재 리스트 + 자재 개별 조회
    List<MaterialModel> getListMaterial();
    int getCountListMaterial();
    MaterialModel getOneMaterial(String materialUniqId);

    //자재등록
    int registerMaterial(MaterialModel materialModel);

    int updateMaterial(MaterialModel materialModel);

    int deleteMaterial(String materialUniqId);


}



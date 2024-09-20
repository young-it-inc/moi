package com.youngit.office.api.material.mapper;

import com.youngit.office.api.material.dto.MaterialSearchDto;
import com.youngit.office.api.material.model.MaterialModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {

    /**
     *자재 리스트 조회 및 검색 + 자재 개별 조회
     */
    List<MaterialModel> getOrSearchListMaterial(MaterialSearchDto materialSearchDto);
    int countGetOrSearchListMaterial(MaterialSearchDto materialSearchDto);
    MaterialModel getOneMaterial(String materialUniqId);

    /**
     * 자재 등록
     */
    int registerMaterial(MaterialModel materialModel);
    String getLastMaterialUniqId();

    /**
     * 자재 수정
     */
    int updateMaterial(MaterialModel materialModel);


    int deleteMaterial(String materialUniqId);



}



package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.model.AsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsService {

    private final AsMapper asMapper;

    @Autowired
    public AsService(AsMapper asMapper) {
        this.asMapper = asMapper;
    }

    public AsDto convertToDto(AsModel asModel) {
        return asMapper.toDto(asModel);
    }

    public List<AsDto> convertToDtoList(List<AsModel> asModelList) {
        return asMapper.toDtoList(asModelList);
    }

    public AsModel convertToModel(AsDto asDto) {
        return asMapper.toModel(asDto);
    }

    public List<AsDto> getListAs(String asStateCode) {
        List<AsModel> asModelList = asMapper.getListAs(asStateCode);
        return convertToDtoList(asModelList);
    }

    public int getCountListAs(String asStateCode) {
        return asMapper.getCountListAs(asStateCode);
    }

    public AsDto getOneAs(String asUniqId) {
        AsModel asModel = asMapper.getOneAs(asUniqId);
        return convertToDto(asModel);
    }

    public int registerAs(AsDto asDto) {
        AsModel asModel = convertToModel(asDto);
        return asMapper.registerAs(asModel);
    }

    public int updateAs(AsDto asDto) {
        int result = 0;
        AsModel asModel = convertToModel(asDto);
        return asMapper.updateAs(asModel);
    }

    public int deleteAs(String asUniqId) {

        return asMapper.deleteAs(asUniqId);
    }

}

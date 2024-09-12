package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.mapper.AsMapstructMapper;
import com.youngit.office.api.as.model.AsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsService {
    private final AsMapper asMapper;
    private final AsMapstructMapper asMapstructMapper;

    @Autowired
    public AsService(AsMapper asMapper, AsMapstructMapper asMapstructMapper) {
        this.asMapper = asMapper;
        this.asMapstructMapper = asMapstructMapper;
    }


    public List<AsDto> getListAs(String asStateCode) {
        List<AsModel> asModelList = asMapper.getListAs(asStateCode);
        return asMapstructMapper.toDtoList(asModelList);
    }
    public int getCountListAs(String asStateCode) {
        return asMapper.getCountListAs(asStateCode);
    }
    public AsDto getOneAs(String asUniqId) {
        AsModel asModel = asMapper.getOneAs(asUniqId);
        return asMapstructMapper.toDto(asModel);
    }


    public int registerAs(AsDto asDto) {
        AsModel asModel = asMapstructMapper.toModel(asDto);
        return asMapper.registerAs(asModel);
    }
    public int updateAs(AsDto asDto) {
        int result = 0;
        AsModel asModel = asMapstructMapper.toModel(asDto);
        return asMapper.updateAs(asModel);
    }
    public int deleteAs(String asUniqId) {
        return asMapper.deleteAs(asUniqId);
    }

}

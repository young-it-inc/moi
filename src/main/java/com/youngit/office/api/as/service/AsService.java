package com.youngit.office.api.as.service;

import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.model.AsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsService {

    @Autowired
    public AsMapper asMapper;

    public List<AsModel> getListAs(String asStateCode) {
        return asMapper.getListAs(asStateCode);
    }

    public int getCountListAs(String asStateCode) {
        return asMapper.getCountListAs(asStateCode);
    }

    public AsModel getOneAs(String asUniqId) {
        return asMapper.getOneAs(asUniqId);
    }

    public int registerAs(AsModel asModel) {
        return asMapper.registerAs(asModel);
    }

    public int updateAs(AsModel asModel) {
        return asMapper.updateAs(asModel);
    }

    public int deleteAs(String asUniqId) {
        return asMapper.deleteAs(asUniqId);
    }


}

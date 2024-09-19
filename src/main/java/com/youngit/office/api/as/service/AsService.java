package com.youngit.office.api.as.service;

import com.youngit.office.api.as.dto.AsDto;
import com.youngit.office.api.as.mapper.AsMapper;
import com.youngit.office.api.as.mapstruct.AsMapstruct;
import com.youngit.office.api.as.model.AsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsService {

    private final AsMapper asMapper;
    private final AsMapstruct asMapstruct;

    @Autowired
    public AsService(AsMapper asMapper, AsMapstruct asMapstruct) {
        this.asMapper = asMapper;
        this.asMapstruct = asMapstruct;
    }

    /**
     * AS 조회
     * @param asStateCode
     * @return
     */
    public List<AsDto> getListAs(String asStateCode) {
        List<AsModel> asModelList = asMapper.getListAs(asStateCode);
        return asModelList.stream().map(asMapstruct::toDto).toList();
    }
    public int getCountListAs(String asStateCode) {
        return asMapper.getCountListAs(asStateCode);
    }
    public AsDto getOneAs(String asUniqId) {
        AsModel asModel = asMapper.getOneAs(asUniqId);
        return asMapstruct.toDto(asModel);
    }

    /**
     * AS 등록
     * @param asDto
     * @return
     */
    public int registerAs(AsDto asDto) {
        AsModel asModel = asMapstruct.toModel(asDto);
        String newAsUniqId = generateNewAsUniqId(asMapper.getLastAsUniqId());
        asModel.setAsUniqId(newAsUniqId);
        return asMapper.registerAs(asModel);
    }
    private String generateNewAsUniqId(String lastAsUniqId) {
        String newAsUniqId = "";
        if (lastAsUniqId == null) {
            newAsUniqId = "AS_00000000000000001";
        } else {
            String lastAsUniqIdNumber = lastAsUniqId.substring(2);
            long newAsUniqIdNumber = Long.parseLong(lastAsUniqIdNumber) + 1;
            newAsUniqId = "AS_" + String.format("%017d", newAsUniqIdNumber);
        }
        return newAsUniqId;
    }
    /**
     * AS 수정
     * @param asDto
     * @return
     */
    public int updateAs(AsDto asDto) {
        int result = 0;
        AsModel asModel = asMapstruct.toModel(asDto);
        return asMapper.updateAs(asModel);
    }

    /**
     * AS 삭제
     * @param asUniqId
     * @return
     */
    public int deleteAs(String asUniqId) {
        return asMapper.deleteAs(asUniqId);
    }


}

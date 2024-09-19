package com.youngit.office.api.office.service;

import com.youngit.office.api.office.dto.OfficeDto;
import com.youngit.office.api.office.mapper.OfficeMapper;
import com.youngit.office.api.office.mapstruct.OfficeMapstruct;
import com.youngit.office.api.office.model.OfficeModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfficeService {

    private final OfficeMapper officeMapper;

    private final OfficeMapstruct officeMapstruct;
    public OfficeService(OfficeMapper officeMapper, OfficeMapstruct officeMapstruct) {
        this.officeMapper = officeMapper;
        this.officeMapstruct = officeMapstruct;
    }

    public List<OfficeDto> getListOffice() {
        List<OfficeModel> officeModelList = officeMapper.getListOffice();
        return officeModelList.stream().map(officeMapstruct::toDto).toList();
    }
    public int registerOffice(OfficeDto officeDto) {
        //사업소코드 중복 확인 후 등록
        boolean isExistOfficeId = officeMapper.isExistOfficeId(officeDto.getOfficeId());
        if(isExistOfficeId) {
            return -1;
        }
        else {
            OfficeModel officeModel = officeMapstruct.toModel(officeDto);
            return officeMapper.registerOffice(officeModel);
        }
    }

    public int updateOffice(OfficeDto officeDto) {
        OfficeModel officeModel = officeMapstruct.toModel(officeDto);
       return officeMapper.updateOffice(officeModel);
    }

    public int deleteOffice(String officeId) {
        return officeMapper.deleteOffice(officeId);
    }


}

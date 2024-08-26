package com.youngit.office.api.office.service;

import com.youngit.office.api.office.mapper.OfficeMapper;
import com.youngit.office.api.office.model.OfficeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    OfficeMapper officeMapper;

    public List<OfficeModel> getListOffice() {
        return officeMapper.getListOffice();
    }
    public int registerOffice(OfficeModel officeModel) {
        //사업소코드 중복 확인 후 등록
        boolean isExistOfficeId = officeMapper.isExistOfficeId(officeModel.getOfficeId());
        if(isExistOfficeId) {
            return -1;
        }
        else
        {
            return officeMapper.registerOffice(officeModel);
        }
    }

    public int updateOffice(OfficeModel officeModel) {
        return officeMapper.updateOffice(officeModel);
    }

    public int deleteOffice(String officeId) {
        return officeMapper.deleteOffice(officeId);
    }


}

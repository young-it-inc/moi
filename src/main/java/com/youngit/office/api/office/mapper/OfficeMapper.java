package com.youngit.office.api.office.mapper;

import com.youngit.office.api.office.dto.OfficeSearchDto;
import com.youngit.office.api.office.model.OfficeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OfficeMapper {

    int registerOffice(OfficeModel officeModel);
    int updateOffice(OfficeModel officeModel);

    boolean isExistOfficeId(String officeId);
    int deleteOffice(String officeId);

    List<OfficeModel> getOrSearchListOffice(OfficeSearchDto officeSearchDto);
}

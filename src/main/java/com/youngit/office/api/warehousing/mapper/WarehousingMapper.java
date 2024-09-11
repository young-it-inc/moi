package com.youngit.office.api.warehousing.mapper;

import com.youngit.office.api.warehousing.dto.WarehousingDto;
import com.youngit.office.api.warehousing.model.WarehousingModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehousingMapper {

    WarehousingDto toDto(WarehousingModel warehousingModel);
}

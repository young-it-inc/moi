package com.youngit.office.api.log.mapper;

import com.youngit.office.api.log.model.LogModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void insertLog(LogModel logModel);
}

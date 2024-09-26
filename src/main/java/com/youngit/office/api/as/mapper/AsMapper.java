package com.youngit.office.api.as.mapper;

import com.youngit.office.api.as.dto.AsSearchDto;
import com.youngit.office.api.as.model.AsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AsMapper {

    List<AsModel> getOrSearchListAs(AsSearchDto asSearchDto);
    int countGetOrSearchListAs(AsSearchDto asSearchDto);
    AsModel getOneAs(String asUniqId);

    String getLastAsUniqId();
    int registerAs(AsModel asModel);
    int updateAs(AsModel asModel);
    int deleteAs(String asUniqId);

}

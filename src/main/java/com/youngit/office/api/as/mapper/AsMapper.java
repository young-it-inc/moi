package com.youngit.office.api.as.mapper;

import com.youngit.office.api.as.model.AsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AsMapper {




    List<AsModel> getListAs(String asStateCode);

    AsModel getOneAs(String asUniqId);

    int getCountListAs(String asStateCode);


    int registerAs(AsModel asModel);

    int updateAs(AsModel asModel);

    int deleteAs(String asUniqId);

}

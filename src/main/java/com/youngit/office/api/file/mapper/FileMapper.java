package com.youngit.office.api.file.mapper;

import com.youngit.office.api.file.model.FileModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    int insertFile(FileModel fileModel);
    int updateFile(FileModel fileModel);
    int deleteFile(String fileUniqId);

}

package com.youngit.office.api.member.mapper;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.model.MemberModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapstructMapper {


    MemberDto toDto(MemberModel memberModel);
    MemberModel toModel(MemberDto memberDto);
    List<MemberDto> toDtoList(List<MemberModel> memberModelList);
    List<MemberModel> toModelList(List<MemberDto> memberDtoList);


}

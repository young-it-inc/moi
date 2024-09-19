package com.youngit.office.api.member.mapstruct;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.model.MemberModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapstruct {

    MemberDto toDto(MemberModel memberModel);
    MemberModel toModel(MemberDto memberDto);

}

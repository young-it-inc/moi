package com.youngit.office.api.member.mapper;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    MemberDto toDto(MemberModel memberModel);
    MemberModel toModel(MemberDto memberDto);
    List<MemberDto> toDtoList(List<MemberModel> memberModelList);
    List<MemberModel> toModelList(List<MemberDto> memberDtoList);


    List<MemberModel> getListMember();
    int getCountListMember();
    int registerMember(MemberModel memberModel);

    String getLastMemberUniqId();
    boolean checkMemberId(String id);

    int updateMember(MemberModel memberModel);

    int deleteMember(String id);

    MemberModel login(MemberModel memberModel);
}

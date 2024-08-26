package com.youngit.office.api.member.mapper;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberModel> getListMember(MemberDto memberDto);
    int registerMember(MemberModel memberModel);

    String getLastMemberUniqId();
    boolean checkMemberId(String id);

    int updateMember(MemberModel memberModel);

    int deleteMember(String id);

    MemberModel login(MemberModel memberModel);
}

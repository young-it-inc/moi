package com.youngit.office.api.member.mapper;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberModel> getListMember();
    int getCountListMember();

    MemberModel getOneMember(String memberId);
    int registerMember(MemberModel memberModel);

    String getLastMemberUniqId();
    boolean checkMemberId(String memberId);

    int updateMember(MemberModel memberModel);

    int deleteMember(String memberId);

    MemberModel login(MemberModel memberModel);
}

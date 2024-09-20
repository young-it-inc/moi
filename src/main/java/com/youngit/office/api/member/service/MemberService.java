package com.youngit.office.api.member.service;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.dto.MemberSearchDto;
import com.youngit.office.api.member.mapper.MemberMapper;
import com.youngit.office.api.member.mapstruct.MemberMapstruct;
import com.youngit.office.api.member.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {



    private final MemberMapper memberMapper;
    private final MemberMapstruct memberMapstruct;

    @Autowired
    public MemberService(MemberMapper memberMapper, MemberMapstruct memberMapstruct) {
        this.memberMapper = memberMapper;
        this.memberMapstruct = memberMapstruct;
    }

    public List<MemberDto> getOrSearchListMember(MemberSearchDto memberSearchDto) {
        List<MemberModel> result = memberMapper.getOrSearchListMember(memberSearchDto);
        return result.stream().map(memberMapstruct::toDto).toList();
    }

    public int countGetOrSearchListMember(MemberSearchDto memberSearchDto) {
        return memberMapper.countGetOrSearchListMember(memberSearchDto);
    }
    public MemberDto getOneMember(String memberId)
    {
        MemberModel result = memberMapper.getOneMember(memberId);
        return memberMapstruct.toDto(result);
    }

    public int registerMember(MemberDto memberDto) {
        //memberUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastMemberUniqId = memberMapper.getLastMemberUniqId();
        String newMemberUniqId = generateNewMemberUniqId(lastMemberUniqId);
        memberDto.setMemberUniqId(newMemberUniqId);
        MemberModel memberModel = memberMapstruct.toModel(memberDto);
        return memberMapper.registerMember(memberModel);
    }


    private String generateNewMemberUniqId(String lastMemberUniqId) {
        if(lastMemberUniqId == null)
            return "USRCNFRM_00000000001";

        String prefix = "USRCNFRM_";
        String numberPart = lastMemberUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNumberStr = String.format("%011d", newNumber); //0으로 패딩하여 11자리로 맞춤
        return prefix + newNumberStr;
    }

    public boolean checkMemberId(String id) {
        return memberMapper.checkMemberId(id);
    }

    public int updateMember(MemberDto memberDto) {
        MemberModel memberModel = memberMapstruct.toModel(memberDto);
        return memberMapper.updateMember(memberModel);
    }

    public int deleteMember(String id) {
        return memberMapper.deleteMember(id);
    }

    public MemberDto login(MemberDto memberDto) {
        MemberModel memberModel = memberMapstruct.toModel(memberDto);
        MemberModel result = memberMapper.login(memberModel);
        return memberMapstruct.toDto(result);
    }
}

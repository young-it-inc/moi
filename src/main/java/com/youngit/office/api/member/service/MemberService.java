package com.youngit.office.api.member.service;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.mapper.MemberMapper;
import com.youngit.office.api.member.mapper.MemberMapstructMapper;
import com.youngit.office.api.member.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberMapstructMapper memberMapstructMapper;


    @Autowired
    public MemberService(MemberMapper memberMapper, MemberMapstructMapper memberMapstructMapper) {

        this.memberMapper = memberMapper;
        this.memberMapstructMapper = memberMapstructMapper;
    }


    public List<MemberDto> getListMember() {
        List<MemberModel> result = memberMapper.getListMember();
        return result.stream().map(memberMapstructMapper::toDto).toList();
    }

    public MemberDto getOneMember(String memberId)
    {
        MemberModel result = memberMapper.getOneMember(memberId);
        return memberMapstructMapper.toDto(result);
    }

    public int registerMember(MemberDto memberDto) {
        //memberUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastMemberUniqId = memberMapper.getLastMemberUniqId();
        String newMemberUniqId = generateNewMemberUniqId(lastMemberUniqId);
        memberDto.setMemberUniqId(newMemberUniqId);
        MemberModel memberModel = memberMapstructMapper.toModel(memberDto);
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
        MemberModel memberModel = memberMapstructMapper.toModel(memberDto);
        return memberMapper.updateMember(memberModel);
    }

    public int deleteMember(String id) {
        return memberMapper.deleteMember(id);
    }

    public MemberDto login(MemberDto memberDto) {
        MemberModel memberModel = memberMapstructMapper.toModel(memberDto);
        MemberModel result = memberMapper.login(memberModel);
        return memberMapstructMapper.toDto(result);
    }
}

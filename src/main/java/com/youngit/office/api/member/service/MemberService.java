package com.youngit.office.api.member.service;

import com.youngit.office.api.member.dto.MemberDto;
import com.youngit.office.api.member.mapper.MemberMapper;
import com.youngit.office.api.member.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    public List<MemberModel> getListMember(MemberDto memberDto) {

        return memberMapper.getListMember(memberDto);
    }

    public int registerMember(MemberModel memberModel) {
        //memberUiqId: 고유번호 등록하기 위해 가장 최근 고유번호 가져옴.
        String lastMemberUniqId = memberMapper.getLastMemberUniqId();
        String newMemberUniqId = generateNewMemberUniqId(lastMemberUniqId);
        memberModel.setMemberUniqId(newMemberUniqId);
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

    public int updateMember(MemberModel memberModel) {
        return memberMapper.updateMember(memberModel);
    }

    public int deleteMember(String id) {
        return memberMapper.deleteMember(id);
    }

    public MemberModel login(MemberModel memberModel) {
        return memberMapper.login(memberModel);
    }
}

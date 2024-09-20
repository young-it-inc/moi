package com.youngit.office.api.contract.dto;

public class ContractSearchDto {

    String keyword; //검색어
    String contractType; //계약유형(지자체: gov/일반: gen)

    //팀별 조회
    //제품별 계약 수량/합계 조회
    //계약 연도별 조회 (내년 유지보수계약은 올해 12월 계약체결. 계약일 기준 조회하면 당해년도 매출에 합산되는 단점)


}

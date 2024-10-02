package com.youngit.office.api.member;

public enum Role {

    ADMIN("관리자"),
    EMPLOYEE("직원"),
    SALES("영업팀"),
    INSTALLER("설치팀"),
    GUEST("게스트");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }


}

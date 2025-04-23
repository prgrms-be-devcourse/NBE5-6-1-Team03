package com.grepp.gridncircle.app.model.member.code;

import org.apache.ibatis.type.Alias;

@Alias("MEMBER_ROLE")
public enum Role {
    ANONYMOUS,
    ROLE_USER,
    ROLE_ADMIN
}

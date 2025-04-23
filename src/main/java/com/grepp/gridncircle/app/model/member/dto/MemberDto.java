package com.grepp.gridncircle.app.model.member.dto;

import com.grepp.gridncircle.infra.auth.code.Role;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MemberDto {

    private String id;
    private String password;
    private Role role;
    private String tel;
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String name;
    private String address;
}

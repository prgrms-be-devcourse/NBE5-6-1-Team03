package com.grepp.gridncircle.app.model.member.dto;

import com.grepp.gridncircle.app.model.member.code.Role;
import lombok.Data;

import java.time.LocalDateTime;

// 비횐 횐 같은 이멜로 못가입하게! 체크.
@Data
public class Member {
    private String UserId;
    private String password;
    private Role role;
    private String tel;
    private String email;
    private LocalDateTime created_at;
    private String name;
    private String address;
}

package com.grepp.gridncircle.app.controller.web.member.form;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupForm {

    @NotBlank
    private String id;
    @NotBlank
    @Size(min = 4, max = 10)
    private String password;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 14)
    private String tel;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    public MemberDto toDto() {
        MemberDto member = new MemberDto();
        member.setId(id);
        member.setPassword(password);
        member.setEmail(email);
        member.setTel(tel);
        member.setName(name);
        member.setAddress(address);
        return member;
    }
}
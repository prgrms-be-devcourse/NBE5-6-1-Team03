package com.grepp.gridncircle.app.controller.web.member.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 4, max = 10)
    private String password;
}

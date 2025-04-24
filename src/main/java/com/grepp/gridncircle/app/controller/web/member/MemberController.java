package com.grepp.gridncircle.app.controller.web.member;

import com.grepp.gridncircle.app.controller.web.member.form.LoginForm;
import com.grepp.gridncircle.app.controller.web.member.form.SignupForm;
import com.grepp.gridncircle.app.model.member.MemberService;
import com.grepp.gridncircle.infra.auth.code.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("login")
    public String login(LoginForm form, Model model) {
        return "member/login";
    }

    @GetMapping("signup")
    public String signup(SignupForm form){
        return "member/signup";
    }

    @PostMapping("signup")
    public String signup(
            @Valid SignupForm form,
            BindingResult bindingResult,
            Model model){

        if(bindingResult.hasErrors()){
            return "member/signup";
        }

        memberService.signup(form.toDto(), Role.ROLE_USER);
        return "redirect:/";
    }
}

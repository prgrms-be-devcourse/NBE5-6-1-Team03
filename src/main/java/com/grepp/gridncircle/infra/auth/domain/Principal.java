package com.grepp.gridncircle.infra.auth.domain;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Principal extends User {
    
    public Principal(String username, String password,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
    public static Principal createPrincipal(MemberDto member,
        List<SimpleGrantedAuthority> authorities){
        return new Principal(member.getId(), member.getPassword(), authorities);
    }
}

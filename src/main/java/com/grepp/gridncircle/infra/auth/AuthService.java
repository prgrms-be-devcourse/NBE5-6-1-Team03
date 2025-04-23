package com.grepp.gridncircle.infra.auth;

import com.grepp.gridncircle.app.model.member.MemberRepository;
import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import com.grepp.gridncircle.infra.auth.domain.Principal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {
    
    private final MemberRepository memberRepository;

    
    @Override
    public UserDetails loadUserByUsername(String id){
        
        MemberDto member = memberRepository.selectById(id)
                            .orElseThrow(() -> new UsernameNotFoundException(id));
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().name()));
        

        return Principal.createPrincipal(member, authorities);
    }
    
    
}

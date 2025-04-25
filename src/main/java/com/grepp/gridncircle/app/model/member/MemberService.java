package com.grepp.gridncircle.app.model.member;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import com.grepp.gridncircle.infra.auth.code.Role;
import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<MemberDto> selectById(String id) {
        return memberRepository.selectById(id);
    }

    @Transactional
    public void signup(MemberDto dto, Role role) {
        if(memberRepository.existsMember(dto.getId()))
            throw new CommonException(ResponseCode.BAD_REQUEST);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        dto.setRole(role);
        memberRepository.insert(dto);
    }

    public Boolean isDuplicatedId(String id) {
        return memberRepository.existsMember(id);
    }
}

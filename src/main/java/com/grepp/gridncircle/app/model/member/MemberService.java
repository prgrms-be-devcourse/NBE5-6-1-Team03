package com.grepp.gridncircle.app.model.member;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<MemberDto> selectById(String id) {
        return memberRepository.selectById(id);
    }

}

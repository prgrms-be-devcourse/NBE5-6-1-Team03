package com.grepp.gridncircle.app.model.member;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    Optional<MemberDto> selectById(String id);
}

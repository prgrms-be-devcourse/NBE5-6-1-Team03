package com.grepp.gridncircle.app.model.member;

import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {

    Optional<MemberDto> selectById(String id);

    @Select("select count(*) from member where id = #{id}")
    boolean existsMember(String id);

    @Insert("insert into member (ID, PASSWORD, EMAIL, TEL, ROLE, ADDRESS, NAME) "
            + "values(#{id}, #{password}, #{email}, #{tel}, #{role}, #{address}, #{name})")
    void insert(MemberDto dto);
}

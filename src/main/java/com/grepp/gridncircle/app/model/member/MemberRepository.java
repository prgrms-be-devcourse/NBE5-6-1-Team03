package com.grepp.gridncircle.app.model.member;

import com.grepp.gridncircle.app.model.member.dto.Member;
import com.grepp.gridncircle.app.model.member.dto.MemberInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Optional;


@Mapper
public interface MemberRepository {
    Optional<Member> selectById(String userId);

    @Select("select count(*) from member where USER_ID=#{userId}")
    Boolean existsMember(String userId);

    @Insert("insert into member(USER_ID, PASSWORD, EMAIL, TEL, ROLE)"
            + "VALUES (#{userId}, #{password}, #{email}, #{tel}, #{role})")
    void insert(Member dto);

    @Insert("insert into member_info(USER_ID) values (#{userId})")
    void insertInfo(MemberInfo memberInfo);
}

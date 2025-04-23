package com.grepp.gridncircle.app.model.member.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("MemberInfo")
public class MemberInfo {
    private String userId;
    private LocalDateTime createdAt = LocalDateTime.now();

}

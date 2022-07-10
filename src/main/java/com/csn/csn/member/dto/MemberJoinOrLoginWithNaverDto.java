package com.csn.csn.member.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class MemberJoinOrLoginWithNaverDto {
    private String loginId;
    private String email;
    private String name;
}

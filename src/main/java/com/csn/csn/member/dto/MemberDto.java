package com.csn.csn.member.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {

    private String loginId;
    private String password;
    private String name;
    private LocalDate birthday;
    private String email;
    private String phone;

}

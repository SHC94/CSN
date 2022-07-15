package com.csn.csn.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberEmailAuthDto {
    @NotEmpty
    private String authCode;
}

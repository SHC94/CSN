package com.csn.csn.member.entity;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.dto.MemberJoinOrLoginWithNaverDto;
import com.csn.csn.member.dto.MemberLoginDto;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;
    private String name;
    private LocalDate birthday;
    private String email;
    private String phone;

    protected Member() {}

    public Member(String loginId, String password, String name, LocalDate birthday, String email, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }

    public Member(MemberJoinDto memberJoinDto) {
        loginId = memberJoinDto.getLoginId();
        password = memberJoinDto.getPassword();
        name = memberJoinDto.getName();
        birthday = memberJoinDto.getBirthday();
        email = memberJoinDto.getEmail();
        phone = memberJoinDto.getPhone();
    }

    public Member(MemberJoinOrLoginWithNaverDto memberJoinOrLoginWithNaverDto) {
        loginId = memberJoinOrLoginWithNaverDto.getLoginId();
        email = memberJoinOrLoginWithNaverDto.getEmail();
        name = memberJoinOrLoginWithNaverDto.getName();
    }
}

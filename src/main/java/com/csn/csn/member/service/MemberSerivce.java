package com.csn.csn.member.service;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.dto.MemberJoinOrLoginWithNaverDto;
import com.csn.csn.member.dto.MemberLoginDto;
import com.csn.csn.member.entity.Member;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberSerivce {

    public boolean canLogIn(MemberLoginDto memberLoginDto);

    // 회원 가입
    public void join(MemberJoinDto memberJoinDto);

    // 네이버 로그인 및 회원가입
    public void joinOrLoginWithNaver(MemberJoinOrLoginWithNaverDto memberJoinOrLoginWithNaverDto);

    // 아이디 찾기
    public String findLoginID(HttpSession httpSession,
                              String AuthenticationCode,
                              LocalDateTime nowTime) throws AuthenticationException;
}

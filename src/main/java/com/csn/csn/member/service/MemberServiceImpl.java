package com.csn.csn.member.service;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.dto.MemberJoinOrLoginWithNaverDto;
import com.csn.csn.member.dto.MemberLoginDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    // 회원 로그인 시도
    public boolean canLogIn(MemberLoginDto memberLoginDto) {
        String loginId = memberLoginDto.getLoginId();
        String password = memberLoginDto.getPassword();
        Member findMember = memberRepository
                .findByLoginId(loginId)
                .filter((m) -> m.getPassword().equals(password))
                .orElse(null);

        return findMember != null ? true : false;
    }

    // 회원 가입
    public void join(MemberJoinDto memberJoinDto) {
        String newLoginId = memberJoinDto.getLoginId();
        memberRepository.findByLoginId(newLoginId)
                .ifPresentOrElse((m) -> {
                    throw new IllegalArgumentException("중복된 회원 아이디가 존재합니다.");
                    }, () -> {
                    memberRepository.save(new Member(memberJoinDto));
                });
    }

    public void joinOrLoginWithNaver(MemberJoinOrLoginWithNaverDto memberJoinOrLoginWithNaverDto) {
        String loginId = memberJoinOrLoginWithNaverDto.getLoginId();
        memberRepository.findByLoginId(loginId)
                .ifPresentOrElse((m) -> {
                    // do nothing...
                }, () -> {
                    memberRepository.save(new Member(memberJoinOrLoginWithNaverDto));
                });
    }
}

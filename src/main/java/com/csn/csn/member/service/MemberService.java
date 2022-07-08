package com.csn.csn.member.service;

import com.csn.csn.member.dto.MemberLoginDto;
import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 로그인 시도
    public boolean isLoggedIn(MemberLoginDto memberLoginDto) {
        String loginId = memberLoginDto.getLoginId();
        String password = memberLoginDto.getPassword();

        Member findMember = memberRepository
                .findByLoginId(loginId)
                .filter((m) -> m.getPassword().equals(password))
                .orElse(null);

        return findMember != null ? true : false;
    }

    // 회원 아이디 중복 체크
    public boolean hasSameId(String loginId) {
        Member findMember = memberRepository.findByLoginId(loginId).orElse(null);
        return findMember != null ? true : false;
    }

    // 회원 가입
    public void join(MemberJoinDto memberSaveDto) {
        Member newMember = new Member(memberSaveDto);
        memberRepository.save(newMember);
    }
}

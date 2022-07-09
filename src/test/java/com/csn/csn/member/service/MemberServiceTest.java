package com.csn.csn.member.service;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
//    @Rollback(value = false)
    @DisplayName("기본 회원가입 테스트")
    void join_basic() {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz1");
        memberJoinDto.setPassword("1234");
        memberJoinDto.setBirthday(LocalDate.now());
        memberJoinDto.setEmail("lalalalz1@naver.com");
        memberJoinDto.setPhone("01012345678");
        memberJoinDto.setName("test1");

        //when
        Member newMember = new Member(memberJoinDto);
        Long saveMemberId = memberRepository.save(newMember);

        //then
        Optional<Member> findMember = memberRepository.find(newMember.getId());
        assertThat(newMember.getId()).isEqualTo(findMember.get().getId());
    }

    @Test
    @DisplayName()

}
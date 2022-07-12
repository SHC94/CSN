package com.csn.csn.member.service;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.dto.MemberJoinOrLoginWithNaverDto;
import com.csn.csn.member.dto.MemberLoginDto;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @Test
//    @Rollback(value = false)
    @DisplayName("기본 회원가입 테스트")
    void join_basic() {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz2");
        memberJoinDto.setPassword("1234");
        memberJoinDto.setBirthday(LocalDate.now());
        memberJoinDto.setEmail("lalalalz1@naver.com");
        memberJoinDto.setPhone("01012345678");
        memberJoinDto.setName("test1");

        //when
        memberServiceImpl.join(memberJoinDto);

        //then
        Optional<Member> findMember = memberRepository.findByLoginId("lalalalz2");
        assertThat("lalalalz2").isEqualTo(findMember.get().getLoginId());

    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    void join_duplication_error() {
        // given
        MemberJoinDto memberJoinDto1 = new MemberJoinDto();
        memberJoinDto1.setLoginId("lalalalz2");
        memberJoinDto1.setPassword("1234");
        memberJoinDto1.setBirthday(LocalDate.now());
        memberJoinDto1.setEmail("lalalalz1@naver.com");
        memberJoinDto1.setPhone("01012345678");
        memberJoinDto1.setName("test1");

        MemberJoinDto memberJoinDto2 = new MemberJoinDto();
        memberJoinDto2.setLoginId("lalalalz2");
        memberJoinDto2.setPassword("1234");
        memberJoinDto2.setBirthday(LocalDate.now());
        memberJoinDto2.setEmail("lalalalz1@naver.com");
        memberJoinDto2.setPhone("01012345678");
        memberJoinDto2.setName("test1");

        // when
        memberServiceImpl.join(memberJoinDto1);

        // then
        assertThatThrownBy(() -> memberServiceImpl.join(memberJoinDto2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 회원 아이디가 존재합니다.");
    }

    @Test
    @DisplayName("네이버 회원 연동 로그인 - 회원가입")
    void naverLoginWithJoin() {
        // given
        MemberJoinOrLoginWithNaverDto memberDto = new MemberJoinOrLoginWithNaverDto();
        memberDto.setLoginId("네이버 발급 테스트 아이디");
        memberDto.setName("네이버 로그인 테스트");
        memberDto.setEmail("네이버 로그인 테스트 이메일");

        // when
        memberServiceImpl.joinOrLoginWithNaver(memberDto);
        Member findMember = memberRepository.findByLoginId("네이버 발급 테스트 아이디")
                .orElse(null);

        // then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getLoginId()).isEqualTo("네이버 발급 테스트 아이디");
    }

    @Test
    @DisplayName("네이버 회원 연동 로그인 - 이미 회원가입 상태")
    void naverLoginWithjoin_error() {
        // given
        MemberJoinOrLoginWithNaverDto memberDto = new MemberJoinOrLoginWithNaverDto();
        memberDto.setLoginId("네이버 발급 테스트 아이디1");
        memberDto.setName("네이버 로그인 테스트1");
        memberDto.setEmail("네이버 로그인 테스트 이메일1");

        // when
        memberServiceImpl.joinOrLoginWithNaver(memberDto);
        memberServiceImpl.joinOrLoginWithNaver(memberDto);

        // then
        assertThat(1).isEqualTo(memberRepository.findAll()
                .stream()
                .filter((m) -> m.getLoginId().equals("네이버 발급 테스트 아이디1"))
                .count()
        );
    }

    @Test
    @DisplayName("로그인 - 로그인 성공")
    void login() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz");
        memberJoinDto.setPassword("hello!123");
        memberServiceImpl.join(memberJoinDto);

        // when
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setLoginId("lalalalz");
        memberLoginDto.setPassword("hello!123");
        boolean isLoggedIn = memberServiceImpl.canLogIn(memberLoginDto);

        //then
        assertThat(isLoggedIn).isTrue();
    }

    @Test
    @DisplayName("로그인 - 로그인 실패(아이디 맞지 않음)")
    void login_error1() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz");
        memberJoinDto.setPassword("hello!123");
        memberServiceImpl.join(memberJoinDto);

        // when
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setLoginId("lalalalz1");
        memberLoginDto.setPassword("hello!123");
        boolean isLoggedIn = memberServiceImpl.canLogIn(memberLoginDto);

        //then
        assertThat(isLoggedIn).isFalse();
    }

    @Test
    @DisplayName("로그인 - 로그인 실패(비밀번호 맞지 않음)")
    void login_error2() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz");
        memberJoinDto.setPassword("hello!123");
        memberServiceImpl.join(memberJoinDto);

        // when
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setLoginId("lalalalz");
        memberLoginDto.setPassword("hello!12");
        boolean isLoggedIn = memberServiceImpl.canLogIn(memberLoginDto);

        //then
        assertThat(isLoggedIn).isFalse();
    }

    @Test
    @DisplayName("로그인 - 로그인 실패(아이디, 비밀번호 맞지 않음)")
    void login_error3() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz");
        memberJoinDto.setPassword("hello!123");
        memberServiceImpl.join(memberJoinDto);

        // when
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setLoginId("lalalalz1");
        memberLoginDto.setPassword("hello!12");
        boolean isLoggedIn = memberServiceImpl.canLogIn(memberLoginDto);

        //then
        assertThat(isLoggedIn).isFalse();
    }
}
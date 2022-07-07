package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Rollback
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        Member member1 = new Member("lalalalz1", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Member member2 = new Member("lalalalz2", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Member member3 = new Member("lalalalz3", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Member member4 = new Member("lalalalz4", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Member member5 = new Member("lalalalz5", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
    }

    @Test
    @DisplayName("회원을 저장한다.")
    void save() {
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepository.save(member);
    }


    @Test
    @DisplayName("회원을 검색한다.")
    void findOneById() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findOne(savedMemberId);

        //then
        assertThat(member).isEqualTo(findMember);

    }


    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 있을 때")
    void findOneByLoginId_있을때() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findOneByLoginId(member.getLoginId());

        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 없을 때")
    void findOneByLoginId_없을때() {
        //given
        String loginId = "없는 아이디입니다.";

        //when
        Member findMember = memberRepository.findOneByLoginId(loginId);

        //then
        assertThat(findMember).isNull();
    }

    @Test
    @DisplayName("모든 회원을 조회한다.")
    void findAll() {
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(5);

//        List<Member> members2 = memberRepository.findAll();
//        assertThat(members2.size()).isEqualTo(0);
    }
}
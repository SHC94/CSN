package com.csn.csn.member.repository;

import com.csn.csn.member.dto.MemberSaveDto;
import com.csn.csn.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 검색한다.")
    @Transactional
    void findOneById() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findOneById(savedMemberId);

        //then
        assertThat(savedMemberId).isEqualTo(findMember);
    }
}
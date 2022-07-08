package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
//@Transactional
//@Rollback
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
//    @Autowired
//    private EntityManager entityManager;

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
    @DisplayName("중복아이디 회원을 저장한다.")
    void save_중복아이디() {
        Member member1 = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId1 = memberRepository.save(member1);

        Member member2 = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId2 = memberRepository.save(member2);

        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println(member.getId() + " " + member.getLoginId());
        }
    }

    @Test
    @DisplayName("null 회원을 저장한다.")
    void save_error() {
        assertThatThrownBy(() -> memberRepository.save(null))
                .isInstanceOf(NonTransientDataAccessException.class);
    }


    @Test
    @DisplayName("회원을 검색한다.")
    void findOneById() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepository.save(member);

        //when
        Optional<Member> findMember = memberRepository.find(savedMemberId);

        //then
        assertThat(member).isEqualTo(findMember.get());

    }

    @Test
    @DisplayName("없는 회원을 검색한다.")
    void findOneById_Error() {
        //given
        Long savedMemberId = -1L;

        //when
        Optional<Member> findMember = memberRepository.find(savedMemberId);

        //then
        assertThatThrownBy(() -> findMember.get()).isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 있을 때")
    void findOneByLoginId_있을때() {
        //given
        String loginId = "lalalalz";

        //when
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        //then
        assertThat(1L).isEqualTo(findMember.get().getId());
    }

    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 없을 때")
    void findOneByLoginId_없을때() {
        //given
        String loginId = "없는 아이디입니다.";

        //when
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        //then
        assertThatThrownBy(() ->findMember.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("모든 회원을 조회한다.")
    void findAll() {
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원을 지운다.")
    void deleteOne() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepository.save(member);

        //when
        memberRepository.delete(member);

        //then
        Optional<Member> findMember = memberRepository.find(savedMemberId);
        assertThatThrownBy(() -> findMember.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("없는 회원을 지운다.")
    void deleteOne_Error() {
        //given
        Member unSavedMember = new Member("go", "asdf", "asdf", LocalDate.now(), "asdf", "asdf"); // 이상한 회원

        memberRepository.delete(unSavedMember);

        //when, then
        assertThatThrownBy(() -> memberRepository.delete(null))
                .isInstanceOf(NonTransientDataAccessException.class);
    }
}
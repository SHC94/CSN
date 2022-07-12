package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Rollback
class MemberRepositoryTest {

    @Autowired
    private MemberRepositoryImpl memberRepositoryImpl;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
//        Member member1 = new Member("lalalalz1", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
//        Member member2 = new Member("lalalalz2", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
//        Member member3 = new Member("lalalalz3", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
//        Member member4 = new Member("lalalalz4", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
//        Member member5 = new Member("lalalalz5", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//        memberRepository.save(member4);
//        memberRepository.save(member5);
    }

    @Test
    @DisplayName("회원을 저장한다.")
    void save() {
        Member member = new Member("lalalalz2", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepositoryImpl.save(member);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("중복아이디 회원을 저장한다.")
    void save_중복아이디() {
        try {
            Member member1 = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
            Long savedMemberId1 = memberRepositoryImpl.save(member1);

            Member member2 = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
            Long save = memberRepositoryImpl.save(member2);

        } catch (Exception e) {
            System.out.println("e = " + e);
        }

//        Assertions.assertThrows(DataIntegrityViolationException.class, () -> memberRepository.save(member2));

    }

    @Test
    @DisplayName("null 회원을 저장한다.")
    void save_error() {
        assertThatThrownBy(() -> memberRepositoryImpl.save(null))
                .isInstanceOf(NonTransientDataAccessException.class);
    }


    @Test
    @DisplayName("회원을 검색한다.")
    void findOneById() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepositoryImpl.save(member);

        //when
        Optional<Member> findMember = memberRepositoryImpl.find(savedMemberId);

        //then
        assertThat(member).isEqualTo(findMember.get());

    }

    @Test
    @DisplayName("없는 회원을 검색한다.")
    void findOneById_Error() {
        //given
        Long savedMemberId = -1L;

        //when
        Optional<Member> findMember = memberRepositoryImpl.find(savedMemberId);

        //then
        assertThatThrownBy(() -> findMember.get()).isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 있을 때")
    void findOneByLoginId_있을때() {
        //given
        Member member = new Member("lalalalz2", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepositoryImpl.save(member);

        //when
        Optional<Member> findMember = memberRepositoryImpl.findByLoginId("lalalalz2");

        //then
        assertThat(member.getId()).isEqualTo(findMember.get().getId());
    }

    @Test
    @DisplayName("로그인 아이디로 회원을 검색한다. -> 없을 때")
    void findOneByLoginId_없을때() {
        //given
        String loginId = "없는 아이디입니다.";

        //when
        Optional<Member> findMember = memberRepositoryImpl.findByLoginId(loginId);

        //then
        assertThatThrownBy(() ->findMember.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("모든 회원을 조회한다.")
    void findAll() {
        List<Member> members = memberRepositoryImpl.findAll();
        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원을 지운다.")
    void deleteOne() {
        //given
        Member member = new Member("lalalalz", "wlstn4050!", "cjs", LocalDate.now(), "lalalalz@naver.com", "0101");
        Long savedMemberId = memberRepositoryImpl.save(member);

        //when
        memberRepositoryImpl.delete(member);

        //then
        Optional<Member> findMember = memberRepositoryImpl.find(savedMemberId);
        assertThatThrownBy(() -> findMember.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("없는 회원을 지운다.")
    void deleteOne_Error() {
        //given
        Member unSavedMember = new Member("go", "asdf", "asdf", LocalDate.now(), "asdf", "asdf"); // 이상한 회원

        memberRepositoryImpl.delete(unSavedMember);

        //when, then
        assertThatThrownBy(() -> memberRepositoryImpl.delete(null))
                .isInstanceOf(NonTransientDataAccessException.class);
    }
}
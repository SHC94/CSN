package com.csn.csn.member.repository;

import com.csn.csn.member.dto.MemberDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.dto.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        log.info("MemberRepository::save::{} 회원 저장완료", member.getId());
        return member.getId();
    }

    public Member findOneById(Long memberId) {
        return em.find(Member.class, memberId);
    }

}

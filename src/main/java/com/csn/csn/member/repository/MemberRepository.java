package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public Member findOneByLoginId(String loginId) {
        List<Member> memberList = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return memberList.isEmpty() ? null : memberList.get(0);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public void deleteOne(Long memberId) {
        em.createQuery("delete from Member m where m.id = :memberId")
                .setParameter("memberId", memberId);
        log.info("MemberRepository::deleteOne::{}회원이 삭제되었습니다.", memberId);
    }
}

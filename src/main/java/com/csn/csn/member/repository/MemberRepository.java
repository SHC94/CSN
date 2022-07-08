package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    public Long save(Member member) {
        log.info("회원을 저장합니다. ={}", member);
        em.persist(member);
        log.info("회원을 저장했습니다. ={}", member);
        return member.getId();
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Optional<Member> find(Long memberId) {
        Member findMember = em.find(Member.class, memberId);
        return Optional.ofNullable(findMember);
    }

    public Optional<Member> findByLoginId(String loginId) {
         List<Member> memberList = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                 .setParameter("loginId", loginId)
                 .getResultList();

         return Optional.ofNullable(
                 memberList.size() > 0 ? memberList.get(0) : null
         );
    }

    public List<Member> findByLoginId2(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
//
//        return Optional.ofNullable(
//                memberList.size() > 0 ? memberList.get(0) : null
//        );
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

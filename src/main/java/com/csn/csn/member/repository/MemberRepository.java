package com.csn.csn.member.repository;

import com.csn.csn.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
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

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

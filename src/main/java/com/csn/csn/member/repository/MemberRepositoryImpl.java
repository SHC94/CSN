package com.csn.csn.member.repository;

import com.csn.csn.exception.DuplicatedLoginIdException;
import com.csn.csn.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }

    @Override
    public Optional<Member> find(Long memberId) {
        Member findMember = em.find(Member.class, memberId);
        return Optional.ofNullable(findMember);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
         List<Member> memberList = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                 .setParameter("loginId", loginId)
                 .getResultList();

        if(memberList.isEmpty()) return Optional.empty();
        return Optional.of(memberList.get(0));
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

package com.csn.csn.member.repository;

import com.csn.csn.exception.DuplicatedLoginIdException;
import com.csn.csn.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    void delete(Member member);

    Optional<Member> find(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByEmail(String email);

    List<Member> findAll();
}

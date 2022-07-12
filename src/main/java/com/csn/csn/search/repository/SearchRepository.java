package com.csn.csn.search.repository;

import com.csn.csn.member.entity.Member;
import com.csn.csn.search.entity.Search;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchRepository {

    Long save(Search search);

    Search find(Long searchId);

    List<Search> findAll();

    List<Search> findByMember(Member member, Integer limit);

    List<Search> findByQuery(String query, Integer limit);

    List<Search> findByBuildTime(LocalDateTime localDateTime, Integer limit);

    void delete(Search search);

}

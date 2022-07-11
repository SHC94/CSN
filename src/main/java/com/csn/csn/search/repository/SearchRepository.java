package com.csn.csn.search.repository;

import com.csn.csn.search.entity.Search;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchRepository {

    Long save(Search search);

    Search find(Long searchId);

    List<Search> findAll();

//    List<Search> findByMemberId(Long memberId); 필요성 검토

    List<Search> findByQuery(String query);

    List<Search> findByBuildTime(LocalDateTime localDateTime);

    void delete(Search search);

}

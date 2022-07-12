package com.csn.csn.search.service;

import com.csn.csn.member.entity.Member;
import com.csn.csn.search.entity.Search;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchService {

    void doSearch(String loginId, String query);

    List<Search> getRecentSearch(Member member);

    public List<Search> getSearchAfterSpecificBuildTime(LocalDateTime buildTime, Integer limit);
}

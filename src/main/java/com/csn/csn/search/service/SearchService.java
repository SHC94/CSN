package com.csn.csn.search.service;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.member.entity.Member;
import com.csn.csn.search.entity.Search;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SearchService {

    void doSearch(String loginId, String query);

    List<Item> doSearch(SearchParam searchParam);

    Optional<Search> doSearchWithSession(HttpSession httpSession, String query);

    List<Search> getRecentSearch(Member member);

    public List<Search> getSearchAfterSpecificBuildTime(LocalDateTime buildTime, Integer limit);
}

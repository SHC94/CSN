package com.csn.csn.search.service;

import com.csn.csn.member.entity.Member;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public void doSearch(Member member, String query) {
        Search search = new Search(member, query);
        searchRepository.save(search);
    }

    public List<Search> getRecentSearch(Member member) {
        return searchRepository.findByMember(member, 10);
    }

    public List<Search> getSearchAfterSpecificBuildTime(LocalDateTime buildTime, Integer limit) {
        if (buildTime == null) {
            throw new IllegalArgumentException("검색 조회 기준 시간이 잘못되었습니다.");
        }

        return searchRepository.findByBuildTime(buildTime, limit);
    }
}

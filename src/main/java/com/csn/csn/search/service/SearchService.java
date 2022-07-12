package com.csn.csn.search.service;

import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final MemberRepository memberRepository;

    public void doSearch(String loginId, String query) {
        memberRepository.findByLoginId(loginId)
                .ifPresent((m) -> {
                    searchRepository.save(new Search(m, query));
                });
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

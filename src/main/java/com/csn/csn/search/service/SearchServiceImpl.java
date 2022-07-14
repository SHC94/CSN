package com.csn.csn.search.service;

import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final MemberRepository memberRepository;

    @Override
    public void doSearch(String loginId, String query) {
        memberRepository.findByLoginId(loginId)
                .ifPresent((m) -> {
                    searchRepository.save(new Search(m, query));
                });
    }

    @Override
    public Optional<Search> doSearchWithSession(HttpSession httpSession, String query) {
        if(httpSession == null) {
            return Optional.empty();
        }

        String loginId = httpSession.getId();
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 회원 아이디 입니다."));

        Search search = new Search(findMember, query);
        searchRepository.save(search);
        return Optional.of(search);
    }

    @Override
    public List<Search> getRecentSearch(Member member) {
        return searchRepository.findByMember(member, 10);
    }

    @Override
    public List<Search> getSearchAfterSpecificBuildTime(LocalDateTime buildTime, Integer limit) {
        if (buildTime == null) {
            throw new IllegalArgumentException("검색 조회 기준 시간이 잘못되었습니다.");
        }

        return searchRepository.findByBuildTime(buildTime, limit);
    }
}

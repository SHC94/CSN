package com.csn.csn.search.service;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import com.csn.csn.member.service.MemberServiceImpl;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class SearchServiceImplTest {

    @Autowired
    private SearchService service;
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceImpl memberService;


    @Test
    @DisplayName("일반 검색")
    void test() {
        // given
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setLoginId("lalalalz1");
        memberService.join(memberJoinDto);

        // when
        String loginId = memberJoinDto.getLoginId();
        String query = "테스트 쿼리";
        service.doSearch(loginId, query);

        //then
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            List<Search> searchList = searchRepository.findByMember(member, 10);

            log.info("{} 회원의 검색 기록 확인", loginId);

            assertThat(searchList.size()).isEqualTo(1);
            assertThat(searchList.get(0).getQuery()).isEqualTo("테스트 쿼리");
        }
        else {
            fail("회원이 조회되어야 합니다.");
        }
    }
}
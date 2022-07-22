package com.csn.csn.search.entity;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.comm.NaverApiCall;
import com.csn.csn.comm.NaverApiConstants;
import com.csn.csn.member.entity.Member;
import com.github.scribejava.apis.NaverApi;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static com.csn.csn.comm.NaverApiCall.*;
import static com.csn.csn.comm.NaverApiConstants.REQUEST_METHOD_GET;

@Entity
@Getter
@ToString
public class Search { // 검색 할 때

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String query;
    private LocalDateTime buildDateTime;

    protected Search() {}

    public Search(Member member, String query) {
        this.member = member;
        this.query = query;
        this.buildDateTime = LocalDateTime.now();
    }

    public Search(Member member, String query, LocalDateTime buildDateTime) {
        this.member = member;
        this.query = query;
        this.buildDateTime = buildDateTime;
    }
}

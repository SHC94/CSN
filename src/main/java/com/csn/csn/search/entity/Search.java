package com.csn.csn.search.entity;

import com.csn.csn.member.entity.Member;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}

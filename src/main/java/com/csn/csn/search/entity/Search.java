package com.csn.csn.search.entity;

import com.csn.csn.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Search { // 검색 할 때

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_id")
    private Member member;
    private String query;
    private LocalDateTime buildDateTime;
}

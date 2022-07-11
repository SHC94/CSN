package com.csn.csn.search.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Search { // 검색 할 때

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    private String query;
    private LocalDateTime buildDateTime;
}

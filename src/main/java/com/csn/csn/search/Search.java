package com.csn.csn.search;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Search {

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    private String query;
    private LocalDateTime buildDate;
}

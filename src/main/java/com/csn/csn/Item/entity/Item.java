package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private LocalDateTime lastBuildDate; // API 요청하여 데이터를 받아온 시간
    private LocalDateTime pubDate; // 실제 데이터 생성일자 (네이버 측)
    private String title;
    private String link;
}

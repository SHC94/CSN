package com.csn.csn.Search;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private LocalDateTime lastBuildDate; // API 요청하여 데이터를 받아온 시간
    private LocalDateTime pubDate; // 실제 데이터 생성일자 (네이버 측)

}

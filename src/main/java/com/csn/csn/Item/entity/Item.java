package com.csn.csn.Item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private LocalDateTime lastBuildDate; // API 요청하여 데이터를 받아온 시간
    private String title;

    @Column(unique = true)
    private String link;

    public Item() {
    }

    public Item(LocalDateTime lastBuildDate, String title, String link) {
        this.lastBuildDate = lastBuildDate;
        this.title = title;
        this.link = link;
    }
}

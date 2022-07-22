package com.csn.csn.Item.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class NewsItem extends Item {

    private String origin;
    private String description;
    private LocalDateTime pubDate;

    protected NewsItem() {
    }

    public NewsItem(LocalDateTime lastBuildDate, String title,
                    String link, String origin, String description, LocalDateTime pubDate) {
        super(lastBuildDate, title, link);
        this.origin = origin;
        this.description = description;
        this.pubDate = pubDate;
    }
}

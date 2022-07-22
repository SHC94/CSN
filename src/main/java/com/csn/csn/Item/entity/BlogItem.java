package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class BlogItem extends Item {

    private String description;
    private String bloggerName;
    private LocalDateTime postDate;

    protected BlogItem() {}

    public BlogItem(LocalDateTime lastBuildDate, String title, String link,
                    String description, String bloggerName, LocalDateTime postDate) {
        super(lastBuildDate, title, link);
        this.description = description;
        this.bloggerName = bloggerName;
        this.postDate = postDate;
    }
}

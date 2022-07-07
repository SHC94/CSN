package com.csn.csn.search;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class BlogItem extends Item {

    private String title;
    private String link;
    private String description;
    private String bloggerName;
    private LocalDateTime postDate;
}

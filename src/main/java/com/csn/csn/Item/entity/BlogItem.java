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
}

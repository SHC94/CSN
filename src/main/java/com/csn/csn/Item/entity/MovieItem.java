package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class MovieItem extends Item {

    private String imageUrl; // 썸네일 이미지 url
    private String subtitle;
    private String director;
    private String actor;
    private Integer userRating;

    protected MovieItem() {}

    public MovieItem(LocalDateTime lastBuildDate, String title, String link, String imageUrl,
                     String subtitle, String director, String actor, Integer userRating) {
        super(lastBuildDate, title, link);
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
    }
}

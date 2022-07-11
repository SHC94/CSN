package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class MovieItem extends Item {

    private String imageUrl; // 썸네일 이미지 url
    private String subtitle;
    private String director;
    private String actor;
    private Integer userRating;
}

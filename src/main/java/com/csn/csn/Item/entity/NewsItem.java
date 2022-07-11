package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class NewsItem extends Item {

    private String origin;
    private String description;
}

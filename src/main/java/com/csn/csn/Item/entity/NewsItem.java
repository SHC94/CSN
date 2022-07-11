package com.csn.csn.Item.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
public class NewsItem extends Item {

    private String origin;
    private String description;

    public NewsItem() {
    }
}

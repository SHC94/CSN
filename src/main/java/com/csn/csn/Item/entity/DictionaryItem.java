package com.csn.csn.Item.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @ToString
@DiscriminatorValue(value = "dictionary")
public class DictionaryItem extends Item {

    private String title;
    private String link;
    private String description;
    private String thumbnail;

    protected DictionaryItem() {}

    public DictionaryItem(String title, String link, String description, String thumbnail) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}

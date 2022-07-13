package com.csn.csn.Item.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@DiscriminatorValue(value = "dictionary")
public class DictionaryItem extends Item {
    private String description;

    @Lob
    private String thumbnail;

    protected DictionaryItem() {}

    public DictionaryItem(LocalDateTime lastBuildDate, String title, String link, String description, String thumbnail) {
        super(lastBuildDate, title, link);
        this.description = description;
        this.thumbnail = thumbnail;
    }
}

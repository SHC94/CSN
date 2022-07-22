package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class ImageItem extends Item {

    private String thumbnail;
    private String sizeHeight; // 타입 고려해봐야됨
    private String sizeWidth;  // 타입 고려해봐야됨

    protected ImageItem() {
    }

    public ImageItem(LocalDateTime lastBuildDate, String title, String link,
                     String thumbnail, String sizeHeight, String sizeWidth) {
        super(lastBuildDate, title, link);
        this.thumbnail = thumbnail;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
    }
}

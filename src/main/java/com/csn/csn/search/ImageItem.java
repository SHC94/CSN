package com.csn.csn.search;

import javax.persistence.Entity;

@Entity
public class ImageItem extends Item {
    private String title;
    private String link;
    private String thumbnail;
    private String sizeHeight; // 타입 고려해봐야됨
    private String sizeWidth;  // 타입 고려해봐야됨
}

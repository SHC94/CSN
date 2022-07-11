package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class ImageItem extends Item {

    private String thumbnail;
    private String sizeHeight; // 타입 고려해봐야됨
    private String sizeWidth;  // 타입 고려해봐야됨
}

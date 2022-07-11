package com.csn.csn.Item.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class BookItem extends Item {

    private String imageUrl;
    private String author;
    private String price;
    private String discount;
    private Integer isbn;
    private String description;
    private LocalDateTime bookPubDate;
}

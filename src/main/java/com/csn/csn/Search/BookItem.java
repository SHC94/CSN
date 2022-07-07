package com.csn.csn.Search;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class BookItem extends Item {
    private String title;
    private String imageUrl;
    private String author;
    private String price;
    private String discount;
    private Integer isbn;
    private String description;
    private LocalDateTime bookPubDate;
}

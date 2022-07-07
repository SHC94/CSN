package com.csn.csn.Search;

import javax.persistence.Entity;

@Entity
public class DictionaryItem extends Item {

    private String title;
    private String link;
    private String description;
    private String thumbnail;
}

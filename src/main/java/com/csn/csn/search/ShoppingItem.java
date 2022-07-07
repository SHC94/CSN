package com.csn.csn.search;

import javax.persistence.Entity;

@Entity
public class ShoppingItem extends Item {

    private String title;
    private String link;
    private String imageUrl;
    private Integer lprice; // 최저가
    private Integer hprice; // 최고가
//    private Product product;  필요시 추가하는 걸로...
}

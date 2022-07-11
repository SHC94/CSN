package com.csn.csn.Item.entity;

import com.csn.csn.Item.entity.Item;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class ShoppingItem extends Item {

    private String imageUrl;
    private Integer lprice; // 최저가
    private Integer hprice; // 최고가
//    private Product product;  필요시 추가하는 걸로...
}

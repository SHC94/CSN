package com.csn.csn.Item.entity;

import com.csn.csn.Item.entity.Item;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
public class ShoppingItem extends Item {

    private String imageUrl;
    private Integer lprice; // 최저가
    private Integer hprice; // 최고가
//    private Product product;  필요시 추가하는 걸로...


    public ShoppingItem() {
    }

    public ShoppingItem(LocalDateTime lastBuildDate, String title, String link,
                        String imageUrl, Integer lprice, Integer hprice) {
        super(lastBuildDate, title, link);
        this.imageUrl = imageUrl;
        this.lprice = lprice;
        this.hprice = hprice;
    }
}

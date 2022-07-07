package com.csn.csn.search;

import javax.persistence.Entity;

@Entity
public class RegionItem extends Item {

    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address; // 임베디트 타임 Address 추가할 것인지 고민 필요
    private String roadAddress; // 임베디트 타임 Address 추가할 것인지 고민 필요
    private Integer mapx;
    private Integer mapy;
}

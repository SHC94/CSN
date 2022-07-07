package com.csn.csn.Search;

import javax.persistence.Entity;

@Entity
public class MovieItem extends Item {

    private String title;
    private String link; // 검색 결과 영화의 하이퍼텍스트 link
    private String imageUrl; // 썸네일 이미지 url
    private String subtitle;
    private String director;
    private String actor;
    private Integer userRating;
}

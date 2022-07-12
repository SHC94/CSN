package com.csn.csn.comm;

import org.springframework.web.bind.annotation.RequestMethod;

public class NaverApiConstants {

    public static final String REQUEST_METHOD_GET   = "GET";
    public static final String REQUEST_METHOD_POST  = "POST";
    public static final Integer RESPONSE_CODE_200   = 200;

    /*FORMAT - JSON  ++ GET */
    public static final String BLOG         = "https://openapi.naver.com/v1/search/blog.json?";         //검색 > 블로그
    public static final String NEWS         = "https://openapi.naver.com/v1/search/news.json?";         //검색 > 뉴스
    public static final String BOOK         = "https://openapi.naver.com/v1/search/book.json?";         //검색 > 책
    public static final String ADULT        = "https://openapi.naver.com/v1/search/adult.json?";        //검색 > 성인 검색어 판별
    public static final String ENCYC        = "https://openapi.naver.com/v1/search/encyc.json?";        //검색 > 백과사전
    public static final String MOVIE        = "https://openapi.naver.com/v1/search/movie.json?";        //검색 > 영화
    public static final String CAFEARTICLE  = "https://openapi.naver.com/v1/search/cafearticle.json?";  //검색 > 카페글
    public static final String KIN          = "https://openapi.naver.com/v1/search/kin.json?";          //검색 > 지식인
    public static final String LOCAL        = "https://openapi.naver.com/v1/search/local.json?";        //검색 > 지역
    public static final String ERRATA       = "https://openapi.naver.com/v1/search/errata.json?";       //검색 > 오타변환
    public static final String WEBKR        = "https://openapi.naver.com/v1/search/webkr.json?";        //검색 > 웹문서
    public static final String IMAGE        = "https://openapi.naver.com/v1/search/image?";             //검색 > 이미지
    public static final String SHOP         = "https://openapi.naver.com/v1/search/shop.json?";         //검색 > 쇼핑
    public static final String DOC          = "https://openapi.naver.com/v1/search/doc.json?";          //검색 > 전문자료
}

package com.csn.csn.search.utils;

import com.csn.csn.Item.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.csn.csn.comm.NaverApiConstants.*;

@Slf4j
public class SearchUtils {

    public static List<Item> generateItemList(String searchType, JSONArray jsonArray, LocalDateTime now) {
        List<Item> itemList = new ArrayList<>();

        if(searchType.equals("BLOG")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                BlogItem blogItem = new BlogItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("descripition"),
                        (String) json.get("bloggerName"),
                        LocalDateTime.parse((String) json.get("postDate"), DateTimeFormatter.RFC_1123_DATE_TIME)

                );

                itemList.add(blogItem);
            }
        }
        else if(searchType.equals("NEWS")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                NewsItem newsItem = new NewsItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("origin"),
                        (String) json.get("description"),
                        LocalDateTime.parse((String) json.get("pubDate"), DateTimeFormatter.RFC_1123_DATE_TIME)
                );

                itemList.add(newsItem);
            }
        }
        else if(searchType.equals("BOOK")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                BlogItem blogItem = new BlogItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("description"),
                        (String) json.get("bloggerName"),
                        LocalDateTime.parse((String) json.get("postDate"), DateTimeFormatter.RFC_1123_DATE_TIME)

                );

                itemList.add(blogItem);
            }
        }
        else if(searchType.equals("ADULT")) {

            // TODO
            //
        }
        else if(searchType.equals("ENCYC")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                DictionaryItem dictionaryItem = new DictionaryItem(
                        now                                     //검색 결과 생성 시간
                        , (String)json.get("title")             //검색 결과 사전 정의의 제목
                        , (String)json.get("link")              //사전 정의 정보 및 추가 정보 link
                        , (String)json.get("description")       //검색 결과 문서의 내용 요약 패키지 정보
                        , (String)json.get("thumbnail")         //검색 결과 이미지 link url
                );

                itemList.add(dictionaryItem);
            }
        }
        else if(searchType.equals("MOVIE")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                MovieItem movieItem = new MovieItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("imageUrl"),
                        (String) json.get("subtitle"),
                        (String) json.get("director"),
                        (String) json.get("actor"),
                        (Integer) json.get("userRating")
                );

                itemList.add(movieItem);
            }
        }
        else if(searchType.equals("CAFEARTICLE")) {
            // TODO
            //
        }
        else if(searchType.equals("KIN")) {
            // TODO
            //
        }
        else if(searchType.equals("LOCAL")) {
            // TODO
            //
        }
        else if(searchType.equals("ERRATA")) {
            // TODO
            //
        }
        else if(searchType.equals("WEBKR")) {
            // TODO
            //
        }
        else if(searchType.equals("IMAGE")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                ImageItem imageItem = new ImageItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("thumbnail"),
                        (String) json.get("sizeHeight"),
                        (String) json.get("sizeWidth")
                );

                itemList.add(imageItem);
            }
        }
        else if(searchType.equals("SHOP")) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                ShoppingItem shoppingItem = new ShoppingItem(
                        now,
                        (String) json.get("title"),
                        (String) json.get("link"),
                        (String) json.get("imageUrl"),
                        (Integer) json.get("lprice"),
                        (Integer) json.get("hprice")
                );

                itemList.add(shoppingItem);
            }
        }
        else {
            throw new IllegalArgumentException("검색 유형 타입이 올바르지 않습니다. " + searchType);
        }

        return itemList;
    }
}

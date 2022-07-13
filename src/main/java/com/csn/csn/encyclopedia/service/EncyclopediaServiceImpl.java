package com.csn.csn.encyclopedia.service;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.entity.NewsItem;
import com.csn.csn.comm.NaverApiCall;
import com.csn.csn.comm.NaverApiConstants;
import com.csn.csn.encyclopedia.repository.EncyclopediaRepository;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncyclopediaServiceImpl implements EncyclopediaService {

    private final EncyclopediaRepository encyclopediaRepository;
    private final NaverApiCall naverApiCall;

    private final SearchService searchService;

    @Value("${api.naver.client_id}")
    private String clientId;

    @Value("${api.naver.client_secret}")
    private String clientSecret;

    /**
     * 백과사전 데이터 조회
     * @return
     */
    @Override
    public List<DictionaryItem> selectDictionaryList() {
        return encyclopediaRepository.selectDictionaryList();
    }//end selectDictionaryList()

    /**
     * 백과사전 검색 데이터 조회
     * @return
     * @throws Exception
     */
    @Override
    public List<DictionaryItem> selectSearchDict(SearchParam searchParam) throws Exception {

        List<DictionaryItem> resultList = new ArrayList<>();

        try {

            //1. NaverAPi call
            //2. result Data Insert
            naverDictionaryApiCall(searchParam);

            //3. Search Data Insert
            searchService.doSearch(searchParam.getId(), searchParam.getQuery());

            //4. data Select

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }//end try catch()

        return resultList;
    }//end selectSearchDict()

    /**
     * 네이버 백과사전 검색 API
     */
    private void naverDictionaryApiCall(SearchParam searchParam) throws Exception {

        //1. NaverAPi call

        // API 기본 정보
        String apiURL = naverApiCall.apiBaseInfo(NaverApiConstants.ENCYC, searchParam.getQuery(), 10, 1, null);
        log.info("apiURL = " + apiURL);

        // API CALL
        HashMap<String, Object> result = naverApiCall.apiCall(apiURL, NaverApiConstants.REQUEST_METHOD_GET, clientId, clientSecret);

        Integer responseCode    = (Integer) result.get("responseCode");
        String responseBody     = (String) result.get("responseBody");
        JSONObject jsonObj      = naverApiCall.NaverApiJsonParsing(responseBody);
        JSONArray items         = (JSONArray) jsonObj.get("items");

        for(int i = 0 ; i < items.size() ; i++) {

            JSONObject item = (JSONObject) items.get(i);
            //LocalDateTime lastBuildDate, String title, String link, String description, String thumbnail
            DictionaryItem dictionaryItem = new DictionaryItem(
                    LocalDateTime.now()                     //검색 결과 생성 시간
                    , (String)item.get("title")             //검색 결과 사전 정의의 제목
                    , (String)item.get("link")              //사전 정의 정보 및 추가 정보 link
                    , (String)item.get("description")       //검색 결과 문서의 내용 요약 패키지 정보
                    , (String)item.get("thumbnail")         //검색 결과 이미지 link url
            );

            //아이템 중복 체크
            Optional<Item> distinctItem = encyclopediaRepository.distinctItem(dictionaryItem);

            //아이템 값이 중복되지 않은 경우 INSERT
            if(!distinctItem.isPresent()) {
                encyclopediaRepository.saveDictionaryItem(dictionaryItem);
            }//end if()

        }//end for()

    }//end naverDictionaryApiCall()

}//end class()

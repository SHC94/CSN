package com.csn.csn.encyclopedia.service;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.entity.NewsItem;
import com.csn.csn.comm.NaverApiCall;
import com.csn.csn.comm.NaverApiConstants;
import com.csn.csn.encyclopedia.repository.EncyclopediaRepository;
import com.csn.csn.encyclopedia.vo.PopularSearch;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
            resultList = encyclopediaRepository.selectSearchDict(searchParam);

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

    /**
     * 인기검색어
     * @return
     */
    @Override
    public List<PopularSearch> popularSearch() {
        return encyclopediaRepository.popularSearch();
    }//end popularSearch()

    /**
     * 통합 검색어 트렌드
     */
    @Override
    public void ApiExamDatalabTrend() {
        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = "{\"startDate\":\"2022-01-01\"," +
                "\"endDate\":\"2022-07-13\"," +
                "\"timeUnit\":\"month\"," +
                "\"keywordGroups\":[{\"groupName\":\"이더리움\"," + "\"keywords\":[\"이더리움\"]}]," +
                "\"device\":\"pc\"," +
                "\"ages\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\"11\"]," +
                "\"gender\":\"m\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);
        log.info("데이터 랩 ===============================================");
        log.info(responseBody);
        log.info("데이터 랩 ===============================================");
    }

    public static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }//end post()


    public static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }//end connect()

    public static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }//end readBody()

}//end class()

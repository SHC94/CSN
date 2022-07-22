package com.csn.csn.comm;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static com.csn.csn.comm.NaverApiConstants.*;


@Slf4j
public class NaverApiCall {

    /**
     * API 기본 정보
     * @param news
     * @param query
     * @param display
     * @param Integer
     * @param sort
     * @return
     * @throws Exception
     */
    public static String apiBaseInfo(String news, String query, int display, int Integer, String sort) throws Exception {
        return news + requestParameterSet(query, display, Integer, sort);
    }//end apiBaseInfo()

    /**
     * 요청 변수 (request parameter)
     * @param query     검색을 원하는 문자열로서 UTF-8로 인코딩한다.
     * @param display   검색 결과 출력 건수 지정
     * @param start     검색 시작 위치로 최대 1000까지 가능
     * @param sort      정렬 옵션: sim (유사도순), date (날짜순)
     * @return
     * @throws Exception
     */
    private static String requestParameterSet(String query, Integer display, Integer start, String sort) throws Exception {
        StringBuilder requestParam = new StringBuilder();

        //검색을 원하는 문자열로서 UTF-8로 인코딩한다.
        String text = URLEncoder.encode(query,"UTF-8");
        requestParam.append("query=" + text);

        //검색 결과 출력 건수 지정
        requestParam.append("&display=" + display);

        //검색 시작 위치로 최대 1000까지 가능
        requestParam.append("&start=" + start);

        //정렬 옵션: sim (유사도순), date (날짜순)
        if(sort != null) requestParam.append("&sort=" + sort);

        return requestParam.toString();
    }//end requestParameterSet()


    /**
     * API CALL
     *
     * @param apiURL
     * @param requestMethod
     * @param clientId
     * @param clientSecret
     * @return
     */
    public static HashMap<String, Object> apiCall(String apiURL, String requestMethod, String clientId, String clientSecret) {

        URL url                         = null;
        HttpURLConnection con           = null;
        HashMap<String, Object> result  = new HashMap<>();

        try {

            //API CALL
            url = new URL(apiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("X-Naver-Client-Id"      , clientId);
            con.setRequestProperty("X-Naver-Client-Secret"  , clientSecret);

            //RESULT CODE
            int responseCode = con.getResponseCode();

            BufferedReader br;
            if (responseCode == 200) br = new BufferedReader(new InputStreamReader(con.getInputStream()));  //정상
            else br = new BufferedReader(new InputStreamReader(con.getErrorStream()));                      //에러

            //RESULT VALUE
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }//end while()

            br.close();

            result.put("responseCode", responseCode);
            result.put("responseBody", response.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }//end apiCall()

    /**
     * String to JSON Parsing
     * @param parsingStr
     * @return
     * @throws ParseException
     */
    public static JSONObject NaverApiJsonParsing(String parsingStr) throws ParseException {
        //1. Parser
        JSONParser jsonParser = new JSONParser();

        //2. To Object
        Object obj = jsonParser.parse(parsingStr);

        //3. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }//end NaverApiJsonParsing()


    /**
     *
     * @param searchType : 검색 유형 (NaverApiConstansts 참고)
     * @param query : 검색어
     * @return
     * @throws Exception
     */
    public static JSONArray callNaverOpenApi(String searchType, String query, String clientId, String clientSecret) {

        HashMap<String, String> apiURLs = getURLs();
        String apiUrl = apiURLs.getOrDefault(searchType, InCorrectURL);

        if (apiUrl.equals(InCorrectURL)) {
            throw new IllegalArgumentException("검색 타입이 올바르지 않습니다.");
        }

        try {

            // 실제 API 호출
            String completeApiUrl = apiBaseInfo(apiUrl, query, 10, 1, null);
            HashMap<String, Object> apiResult = apiCall(completeApiUrl, REQUEST_METHOD_GET, clientId, clientSecret);

            Integer responseCode = (Integer) apiResult.get("responseCode");
            String responseBody = (String) apiResult.get("responseBody");
            JSONObject jsonObj = NaverApiJsonParsing(responseBody);
            JSONArray items = (JSONArray) jsonObj.get("items");

            log.info("callNaverOpenApi = {}, {}", clientSecret, clientId);

            return items;
        }
        catch (ParseException e) {
            // 런타임 예외로 변환
            throw new IllegalArgumentException("API 파싱 에러", e);
        }
        catch (Exception e) {
            // 런타임 예외로 변환
            throw new IllegalStateException("API 호출 에러", e);
        }
    }
}//end class()

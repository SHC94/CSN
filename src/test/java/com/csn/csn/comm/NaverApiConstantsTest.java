package com.csn.csn.comm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class NaverApiConstantsTest {

    @Test
    void 검색유형테스트() {

        HashMap<String, String> urls = NaverApiConstants.getURLs();

        String news = urls.getOrDefault("NEWS", NaverApiConstants.InCorrectURL);

    }
}